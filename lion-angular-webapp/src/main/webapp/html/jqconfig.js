/**
 * Created by wash on 16/3/30.
 */
angular.module("DBApp")
    .constant('JQ_CONFIG',{
        sparkline:   ['../resources/lib/global/plugins/jquery.sparkline.min.js'],
        slimScroll:  ['../resources/lib/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js'],
        vectorMap:   ['../resources/lib/global/plugins/jqvmap/jqvmap/jqvmap.css',
            '../resources/lib/global/plugins/jqvmap/jqvmap/jquery.vmap.js',
            '../resources/lib/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js',
            '../resources/lib/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js',
            '../resources/lib/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js',
            '../resources/lib/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js',
            '../resources/lib/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js',
            '../resources/lib/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js'
        ],
        Morris:   ['../resources/lib/global/plugins/morris/morris.css',
            '../resources/lib/global/plugins/morris/morris.min.js',
            '../resources/lib/global/plugins/morris/raphael-min.js']
    })