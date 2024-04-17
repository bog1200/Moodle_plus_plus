var canvases = document.getElementsByClassName('myChart');

for (var i = 0; i < canvases.length; i++) {
    var ctx = canvases[i].getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Red', 'Blue', 'Yellow'],
            datasets: [{
                data: [10, 20, 30],
                backgroundColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            plugins: {
                animation: {
                    duration: 0  // disable animation
                }
            },
            hover: {
                animationDuration: 0  // duration of animations when hovering an item
            }
        }
    });
}