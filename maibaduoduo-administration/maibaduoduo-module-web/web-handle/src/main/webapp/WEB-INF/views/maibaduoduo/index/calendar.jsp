<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    var dom = document.getElementById("calendar");
    var myChart = echarts.init(dom);
    var app = {};
    var option;
    var cellSize = [90, 90];
    var pieRadius = 30;
    function getVirtulData() {
        var date = +echarts.number.parseDate('2021-09-01');
        var end = +echarts.number.parseDate('2021-10-01');
        var dayTime = 3600 * 24 * 1000;
        var data = [];
        for (var time = date; time < end; time += dayTime) {
            data.push([
                echarts.format.formatTime('yyyy-MM-dd', time),
                Math.floor(Math.random() * 10000)
            ]);
        }
        return data;
    }
    function getPieSeries(scatterData, chart) {
        return scatterData.map(function (item, index) {
            var center = chart.convertToPixel('calendar', item);
            return {
                id: index + 'pie',
                type: 'pie',
                center: center,
                label: {
                    normal: {
                        formatter: '{c}',
                        position: 'inside'
                    }
                },
                radius: pieRadius,
                data: [
                    {name: '工作', value: Math.round(Math.random() * 360)},
                    {name: '娱乐', value: Math.round(Math.random() * 360)},
                    {name: '睡觉', value: Math.round(Math.random() * 360)},
                    {name: '阅读', value: Math.round(Math.random() * 360)}
                ]
            };
        });
    }
    function getPieSeriesUpdate(scatterData, chart) {
        return scatterData.map(function (item, index) {
            var center = chart.convertToPixel('calendar', item);
            return {
                id: index + 'pie',
                center: center
            };
        });
    }
    var scatterData = getVirtulData();
    option = {
        title:{
            text: '个人时间安排',
            left: 20,
            top: 5
        },
        tooltip : {},
        legend: {
            orient: 'vertical',
            data: ['工作', '娱乐', '睡觉','阅读'],
            left: 100,
            top: 50
        },
        calendar: {
            top: 'middle',
            left: 'center',
            orient: 'vertical',
            cellSize: cellSize,
            yearLabel: {
                show: false,
                fontSize: 30
            },
            dayLabel: {
                margin: 20,
                firstDay: 1,
                top:10,
                nameMap: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
            },
            monthLabel: {
                show: false
            },
            range: ['2021-09']
        },
        series: [{
            id: 'label',
            type: 'scatter',
            coordinateSystem: 'calendar',
            symbolSize: 1,
            label: {
                show: true,
                formatter: function (params) {
                    return echarts.format.formatTime('dd', params.value[0]);
                },
                offset: [-cellSize[0] / 2 + 10, -cellSize[1] / 2 + 10],
                fontSize: 14
            },
            data: scatterData
        }]
    };
    var pieInitialized;
    setTimeout(function () {
        pieInitialized = true;
        myChart.setOption({
            series: getPieSeries(scatterData, myChart)
        });
    }, 10);
    app.onresize = function () {
        if (pieInitialized) {
            myChart.setOption({
                series: getPieSeriesUpdate(scatterData, myChart)
            });
        }
    };
    if (option && typeof option === 'object') {
        myChart.setOption(option);
    }
</script>