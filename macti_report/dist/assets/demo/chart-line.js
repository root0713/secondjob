// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

var ctx_tempo_1 = document.getElementById('ctx_tempo_1'); 

var ctx_group1_2 = document.getElementById('ctx_group1_2'); 
var ctx_group1_3 = document.getElementById('ctx_group1_3'); 
var ctx_group1_4 = document.getElementById('ctx_group1_4'); 

var ctx_group2_5 = document.getElementById('ctx_group2_5'); 
var ctx_group2_6 = document.getElementById('ctx_group2_6'); 
var ctx_group2_7 = document.getElementById('ctx_group2_7'); 

var loadTempData = function(){
  var chart1 = new Chart(ctx_tempo_1, { 
    type: 'line', 
    data: chartData_1, 
    // 옵션 
    options: { 
      title: {
        display: true,
        position: 'bottom',
        text: 'Day'
      },      
      responsive: true,
      legend: { 
        display: true
        ,position: 'right' 
      } 
    } 
  });
}

var loadGroup1Data = function(){
  var chart2 = new Chart(ctx_group1_2, { 
    type: 'line', 
    data: chartData_2, 
    // 옵션 
    options: {
      title: {
        display: true,
        position: 'bottom',
        text: '그룹1: 평균 EC'
      },  
      responsive: true,
      legend: { 
        display: true
        ,position: 'right' 
      } 
    } 
  });

  var chart3 = new Chart(ctx_group1_3, { 
    type: 'line', 
    data: chartData_3, 
    // 옵션 
    options: { 
      title: {
        display: true,
        position: 'bottom',
        text: '그룹1: 관수량(초)'
      }, 
      responsive: true,
      legend: { 
        display: true
        ,position: 'right' 
      } 
    } 
  });

  var chart4 = new Chart(ctx_group1_4, { 
    type: 'line', 
    data: chartData_4, 
    // 옵션 
    options: { 
      title: {
        display: true,
        position: 'bottom',
        text: '그룹1: 관수량(톤)'
      }, 
      responsive: true,
      legend: { 
        display: true
        ,position: 'right' 
      } 
    } 
  });
}

var loadGroup2Data = function(){
  var chart5 = new Chart(ctx_group2_5, { 
    type: 'line', 
    data: chartData_5, 
    // 옵션 
    options: { 
      title: {
        display: true,
        position: 'bottom',
        text: '그룹2: 평균 EC'
      }, 
      responsive: true,
      legend: { 
        display: true
        ,position: 'right' 
      } 
    } 
  });

  var chart6 = new Chart(ctx_group2_6, { 
    type: 'line', 
    data: chartData_6, 
    // 옵션 
    options: { 
      title: {
        display: true,
        position: 'bottom',
        text: '그룹2: 관수량(초)'
      }, 
      responsive: true,
      legend: { 
        display: true
        ,position: 'right' 
      } 
    } 
  });

  var chart7 = new Chart(ctx_group2_7, { 
    type: 'line', 
    data: chartData_7, 
    // 옵션 
    options: { 
      title: {
        display: true,
        position: 'bottom',
        text: '그룹2: 관수량(톤)'
      }, 
      responsive: true,
      legend: { 
        display: true
        ,position: 'right' 
      } 
    } 
  });
}

window.onload = function(){
  loadTempData();
}
