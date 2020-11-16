var randomScalingFactor = function() {
  return Math.round(Math.random() * 100);
};

var chartData_1 = { 
  labels: [0, 5, 10, 15, 20, 25, 30], 
  datasets: [{ 
    label: '최저온도',
    data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()], 
    fill: false,
    borderColor: colors[0]
  }, 
  { 
    label: '최고온도',
    data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()], 
    fill: false,
    borderColor: colors[1] 
  }, 
  { 
    label: '최저습도',
    data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()], 
    fill: false,
    borderColor: colors[2] 
  }, 
  { 
    label: '최고습도',
    data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()], 
    fill: false,
    borderColor: colors[3] 
  }    
]};