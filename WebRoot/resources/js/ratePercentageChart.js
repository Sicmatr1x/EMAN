var chart = {
      type: 'bar'
   };
   var title = {
		   
      text: ''   
   };
   var subtitle = {
   };
   var xAxis = {
	categories: ['5⭐️️', '4⭐️', '3⭐️', '2⭐️', '1⭐️'],
      title: {
         text: null
      }
   };
   var yAxis = {
      min: 0,
      title: {
         text: 'Percentage(%)',
         align: 'high'
      },
      labels: {
         overflow: 'justify'
      }
   };
   var tooltip = {
      valueSuffix: ' percentage'
   };
   var plotOptions = {
      bar: {
         dataLabels: {
            enabled: true
         },
         //format:'{y}%'
      }
   };
   var legend = {
      layout: 'vertical',
      align: 'right',
      verticalAlign: 'top',
      x: -40,
      y: 100,
      floating: true,
      borderWidth: 1,
      backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
      shadow: true
   };
   var credits = {
      enabled: false
   };
   
   var series= [{
	   showInLegend: false,
         dataLabels: {
                enabled: true,
                format: '{y} %'
         },
         //name: '评分百分比',
         data: [, 31, 635, 203, 2],

         color:"#FFCC66"
        }
         // {
     //        name: 'Year 1900',
     //        data: [133, 156, 947, 408, 6]
     //    }, {
     //        name: 'Year 2008',
     //        data: [973, 914, 4054, 732, 34]      
	    // }
   ];     
      
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   json.subtitle = subtitle; 
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.series[0].data = [0.33, 0.2, 0.5, 0.5, 0.10];
   json.plotOptions = plotOptions;
   json.legend = legend;
   json.credits = credits;
   
function setChartData(chartData){
	json.series[0].data = chartData;
}

function showChart(){
	$('#rateChart').highcharts(json);
}