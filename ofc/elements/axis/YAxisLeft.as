﻿package elements.axis {
	import flash.display.Sprite;
	
	public class YAxisLeft extends YAxisBase {

		function YAxisLeft() {}
		
		public override function init(json:Object): void {
			//
			// default values for a left axis
			//
			var style:Object = {
				stroke:			2,
				'tick-length':	3,
				colour:			'#784016',
				offset:			false,
				'grid-colour':	'#F5E1AA',
				'grid-visible':	true,
				'3d':			0,
				steps:			1,
				visible:		true,
				min:			0,
				max:			10,
				auto_size:		false
			};
			
			super._init(json, 'y_axis', style);

			this.labels = new YAxisLabelsLeft( this, json );
			this.addChild( this.labels );

			if ( this.labels.i_need_labels )
				this.labels.make_labels(this.style.min, this.style.max, this.style.steps);
		}
		
		public override function resize( label_pos:Number, sc:ScreenCoords ):void {
			
			super.resize_helper( label_pos, sc, false);
		}
	}
}