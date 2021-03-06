﻿package charts.series.bars {

	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.display.Sprite;
	import caurina.transitions.Tweener;
	import caurina.transitions.Equations;
	import flash.geom.Point;
	import global.Global;
	import charts.series.Element;
	import string.Utils;
	
	public class Base extends Element
	{
		protected var tip_pos:flash.geom.Point;
		protected var colour:Number;
		protected var group:Number;
		protected var top:Number;
		protected var bottom:Number;
		protected var mouse_out_alpha:Number;
		private var on_show_animate:Boolean;
		protected var on_show:Properties;
		protected var barWidthPercentage:Number;
		protected var barOverlap:Number;

		
		
		public function Base( index:Number, props:Properties, group:Number )
		{
			super();
			this.index = index;
			this.parse_value(props);
			this.colour = props.get_colour('colour');
				
			this.tooltip = this.replace_magic_values( props.get('tip') );
			this.barWidthPercentage = props.has('barwidth') ? props.get('barwidth') : 0.8;
			this.barOverlap = props.has('overlap') ? props.get('overlap') : 0;

			
			this.group = group;
			this.visible = true;
			this.on_show_animate = true;
			if (props.has('on-show') ) 
				this.on_show = props.get('on-show')
			else
				this.on_show = new Properties({});
				
			
			// remember what our original alpha is:
			this.mouse_out_alpha = props.get('alpha');
			// set the sprit alpha:
			this.alpha = this.mouse_out_alpha;
			
			this.addEventListener(MouseEvent.MOUSE_OVER, this.mouseOver);
			this.addEventListener(MouseEvent.MOUSE_OUT, this.mouseOut);
			
			//
			// This is UGLY!!! We need to decide if we are passing in a SINGLE style object,
			// or many parameters....
			//
			if( props.has('on-click') )
				this.set_on_click( props.get('on-click') );
			if( props.has('on-click-text') )
				this.on_click_text = props.get('on-click-text');
			this.on_click_text = this.replace_magic_values(this.on_click_text);

				
			if( props.has('axis') )
				if( props.get('axis') == 'right' )
					this.right_axis = true;
		}
		
		//
		// most line and bar charts have a single value which is the
		// Y position, some like candle and scatter have many values
		// and will override this method to parse their value
		//
		protected function parse_value( props:Properties ):void {
			
			if ( !props.has('bottom') ) {
				// align to Y min OR zero
				props.set('bottom', Number.MIN_VALUE );
			}
			
			this.top = props.get('top');
			this.bottom = props.get('bottom');
		}
		
		protected function replace_magic_values( t:String ): String {
			if (t != null) {
				var val:Number = this.top - this.bottom;
				t = t.replace('#top#', NumberUtils.formatNumber( this.top ));
				t = t.replace('#bottom#', NumberUtils.formatNumber( this.bottom ));
				t = t.replace('#val#', NumberUtils.formatNumber( val ));
				t = DateUtils.replace_magic_values(t, val);
			}
			
			return t;
		}
		
		
		//
		// for tooltip closest - return the middle point
		//
		public override function get_mid_point():flash.geom.Point {
			
			//
			// bars mid point
			//
			return new flash.geom.Point( this.x + (this.width/2), this.y );
		}
		
		public override function mouseOver(event:Event):void {
			this.is_tip = true;
			Tweener.addTween(this, { alpha:1, time:0.6, transition:Equations.easeOutCirc } );
		}

		public override function mouseOut(event:Event):void {
			this.is_tip = false;
			Tweener.addTween(this, { alpha:this.mouse_out_alpha, time:0.8, transition:Equations.easeOutElastic } );
		}
		
		// override this:
		public override function resize( sc:ScreenCoordsBase ):void {}
		
		//
		// tooltip.left for bars center over the bar
		//
		public override function get_tip_pos(): Object {
			return {x:this.tip_pos.x, y:this.tip_pos.y };
		}
		

		//
		// Called by most of the bar charts.
		// Moves the Sprite into the correct position, then
		// returns the bounds so the bar can draw its self.
		//
		protected function resize_helper( sc:ScreenCoords ):Object {
			var tmp:Object = sc.get_bar_coords(this.index, this.group, this.barWidthPercentage );
			var barWidthAdjustment:Number = tmp.width * this.barOverlap;

			var bar_top:Number = sc.get_y_from_val(this.top, this.right_axis);
			var bar_bottom:Number;
			
			if( this.bottom == Number.MIN_VALUE )
				bar_bottom = sc.get_y_bottom(this.right_axis);
			else
				bar_bottom = sc.get_y_from_val(this.bottom, this.right_axis);
			
			var top:Number;
			var height:Number;
			var upside_down:Boolean = false;
			
			if( bar_bottom < bar_top ) {
				top = bar_bottom;
				upside_down = true;
			}
			else
			{
				top = bar_top;
			}
			
			height = Math.abs( bar_bottom - bar_top );
			
			
			//
			// tell the tooltip where to show its self
			//
			this.tip_pos = new flash.geom.Point( tmp.x + (tmp.width / 2), top );
			
			if ( this.on_show_animate )
				this.first_show(tmp.x, top, height);
			else {
				//
				// move the Sprite to the correct screen location:
				//
				this.y = top;
				this.x = tmp.x;
			this.x = tmp.x - (this.group * barWidthAdjustment);
			}
				
			//
			// return the bounds to draw the item:
			//
			return { width:(tmp.width + barWidthAdjustment), top:top, height:height, upside_down:upside_down };
		}
		
		protected function first_show(x:Number, y:Number, height:Number): void {
			
			this.on_show_animate = false;
			Tweener.removeTweens(this);
			
			// tr.aces('base.as', this.on_show.get('type') );
			var d:Number = x / this.stage.stageWidth;
			d *= this.on_show.get('cascade');
			d += this.on_show.get('delay');
		
			switch( this.on_show.get('type') ) {
				
				case 'pop-up':
					this.x = x;
					this.y = this.stage.stageHeight + this.height + 3;
					Tweener.addTween(this, { y:y, time:1, delay:d, transition:Equations.easeOutBounce } );
					break;
					
				case 'drop':
					this.x = x;
					this.y = -height - 10;
					Tweener.addTween(this, { y:y, time:1, delay:d, transition:Equations.easeOutBounce } );
					break;

				case 'fade-in':
					this.x = x;
					this.y = y;
					this.alpha = 0;
					Tweener.addTween(this, { alpha:this.mouse_out_alpha, time:1.2, delay:d, transition:Equations.easeOutQuad } );
					break;
					
				case 'grow':
					this.x = x;
					this.y = y;
					this.scaleY = 0.01;
					Tweener.addTween(this, { scaleY:1, time:1.2, delay:d, transition:Equations.easeOutQuad } );
					break;
					
				case 'pop':
					this.y = top;
					this.alpha = 0;
					Tweener.addTween(this, { alpha:this.mouse_out_alpha, time:0.7, delay:d, transition:Equations.easeOutQuad } );
					this.scaleX = 0.01;
					this.scaleY = 0.01;
					Tweener.addTween(this, { scaleY:1, scaleX:1, time:1.2, delay:d, transition:Equations.easeOutElastic } );
			
				default:
					this.y = y;
					this.x = x;
				
			}
		}	
	}
}
