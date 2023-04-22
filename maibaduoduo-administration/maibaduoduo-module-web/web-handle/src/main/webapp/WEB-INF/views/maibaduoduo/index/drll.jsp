<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    var dom = document.getElementById("drill");
    var myChartdrill = echarts.init(dom);
    var app = {};

    var optiondrill;



    var uploadedDataURL = '/static/data/drill.json';

    myChartdrill.showLoading();

    $.getJSON(uploadedDataURL, function (rawData) {

        myChartdrill.hideLoading();

        function convert(source, target, basePath) {
            for (var key in source) {
                var path = basePath ? (basePath + '.' + key) : key;
                if (!key.match(/^\$/)) {
                    target.children = target.children || [];
                    var child = {
                        name: path
                    };
                    target.children.push(child);
                    convert(source[key], child, path);
                }
            }

            if (!target.children) {
                target.value = source.$count || 1;
            }
            else {
                target.children.push({
                    name: basePath,
                    value: source.$count
                });
            }
        }

        var data = [];

        convert(rawData, data, '');

        myChartdrill.setOption(optiondrill = {
            title: {
                text: '配置项查询分布',
                subtext: '2021/09',
                left: 'leafDepth'
            },
            tooltip: {},
            series: [{
                name: '配置项查询分布',
                type: 'treemap',
                visibleMin: 300,
                data: data.children,
                leafDepth: 2,
                levels: [
                    {
                        itemStyle: {
                            borderColor: '#555',
                            borderWidth: 1,
                            gapWidth: 1
                        }
                    },
                    {
                        colorSaturation: [0.3, 0.6],
                        itemStyle: {
                            borderColorSaturation: 0.7,
                            gapWidth: 1,
                            borderWidth: 1
                        }
                    },
                    {
                        colorSaturation: [0.3, 0.5],
                        itemStyle: {
                            borderColorSaturation: 0.6,
                            gapWidth: 1
                        }
                    },
                    {
                        colorSaturation: [0.3, 0.5]
                    }
                ]
            }]
        });
    });

    if (optiondrill && typeof optiondrill === 'object') {
        myChartdrill.setOption(optiondrill);
    }

</script>