import {
  Component
} from "react";
// 引入ECharts主模块
import * as echarts from 'echarts';
//按需引入需要的Echart模块
class CdPic extends Component {
  componentDidMount() {
    // 初始化echarts实例，将其挂载到id为main的dom元素上展示


    new Promise<void>((resolve) => {resolve()}).then(() => {
      const myChart = echarts.init(document.getElementById("cd-main"));
      // 绘制图表
      const option = {
        legend: {
          data: ['cycle-15', 'cycle-27', 'cycle-41', 'cycle-116', 'cycle-163']
        },
        series: [
          {
            type: 'graph',
            layout: 'force',
            animation: false,
            label: {
              position: 'right',
              formatter: '{b}'
            },
            lineStyle: {
              curveness: 0.1
            },
            draggable: true,
            edgeSymbol: ['arrow'],
            edgeSymbolSize: 5,
            nodes: [
              { id: '54', name: 'Filter.java', value: 1, category: 1 },
              {
                id: '76',
                name: 'ZuulFilterConcurrencyExceededException.java',
                value: 1,
                category: 1
              },
              { id: '85', name: 'ShouldFilter.java', value: 1, category: 1 },
              { id: '88', name: 'ZuulFilter.java', value: 1, category: 1 },
              { id: '114', name: 'HttpResponseInfo.java', value: 1, category: 1 },
              { id: '111', name: 'HttpRequestInfo.java', value: 1, category: 1 },
              { id: '112', name: 'HttpRequestMessage.java', value: 1, category: 1 },
              {
                id: '115',
                name: 'HttpResponseMessage.java',
                value: 1,
                category: 1
              },
              { id: '68', name: 'SessionContext.java', value: 1, category: 1 },
              { id: '105', name: 'ZuulMessage.java', value: 1, category: 1 },
              { id: '74', name: 'OutboundException.java', value: 1, category: 2 },
              { id: '200', name: 'RequestAttempt.java', value: 1, category: 2 },
              { id: '201', name: 'RequestAttempts.java', value: 1, category: 2 },
              { id: '65', name: 'CommonContextKeys.java', value: 1, category: 2 },
              { id: '211', name: 'CurrentPassport.java', value: 1, category: 2 },
              { id: '31', name: 'EventLoopMetrics.java', value: 1, category: 3 },
              {
                id: '30',
                name: 'EventLoopGroupMetrics.java',
                value: 1,
                category: 3
              },
              {
                id: '37',
                name: 'ElbProxyProtocolChannelHandler.java',
                value: 1,
                category: 4
              },
              {
                id: '162',
                name: 'ClientResponseWriter.java',
                value: 1,
                category: 4
              },
              {
                id: '149',
                name: 'ZuulFilterChainHandler.java',
                value: 1,
                category: 4
              },
              {
                id: '199',
                name: 'OriginTimeoutManager.java',
                value: 1,
                category: 4
              },
              { id: '205', name: 'NettyOrigin.java', value: 1, category: 4 },
              {
                id: '169',
                name: 'OriginResponseReceiver.java',
                value: 1,
                category: 4
              },
              {
                id: '130',
                name: 'ClientChannelManager.java',
                value: 1,
                category: 4
              },
              {
                id: '134',
                name: 'ConnectionPoolHandler.java',
                value: 1,
                category: 4
              },
              {
                id: '136',
                name: 'DefaultOriginChannelInitializer.java',
                value: 1,
                category: 4
              },
              { id: '142', name: 'PooledConnection.java', value: 1, category: 4 },
              {
                id: '131',
                name: 'ClientTimeoutHandler.java',
                value: 1,
                category: 4
              },
              { id: '92', name: 'ProxyEndpoint.java', value: 1, category: 4 },
              { id: '148', name: 'ZuulEndPointRunner.java', value: 1, category: 4 },
              { id: '178', name: 'Http2OrHttpHandler.java', value: 1, category: 4 },
              {
                id: '38',
                name: 'HAProxyMessageChannelHandler.java',
                value: 1,
                category: 4
              },
              { id: '45', name: 'RejectionUtils.java', value: 1, category: 4 },
              {
                id: '161',
                name: 'ClientRequestReceiver.java',
                value: 1,
                category: 4
              },
              { id: '121', name: 'ConnTimer.java', value: 1, category: 4 },
              {
                id: '160',
                name: 'ClientConnectionsShutdown.java',
                value: 1,
                category: 4
              },
              { id: '170', name: 'Server.java', value: 1, category: 4 },
              { id: '120', name: 'ConnCounter.java', value: 1, category: 4 },
              {
                id: '151',
                name: 'PassportLoggingHandler.java',
                value: 1,
                category: 4
              },
              {
                id: '10',
                name: 'Http1ConnectionExpiryHandler.java',
                value: 1,
                category: 4
              },
              { id: '8', name: 'ConnectionCloseType.java', value: 1, category: 4 },
              {
                id: '9',
                name: 'Http1ConnectionCloseHandler.java',
                value: 1,
                category: 4
              },
              {
                id: '159',
                name: 'BaseZuulChannelInitializer.java',
                value: 1,
                category: 4
              },
              {
                id: '7',
                name: 'ConnectionCloseChannelAttributes.java',
                value: 1,
                category: 4
              },
              {
                id: '3',
                name: 'AbstrHttpConnectionExpiryHandler.java',
                value: 1,
                category: 4
              },
              {
                id: '141',
                name: 'PerServerConnectionPool.java',
                value: 1,
                category: 5
              },
              {
                id: '135',
                name: 'DefaultClientChannelManager.java',
                value: 1,
                category: 5
              }
            ],
            links: [
              { source: '65', target: '201' },
              { source: '65', target: '211' },
              { source: '130', target: '142' },
              { source: '131', target: '142' },
              { source: '3', target: '7' },
              { source: '3', target: '8' },
              { source: '68', target: '115' },
              { source: '134', target: '142' },
              { source: '199', target: '205' },
              { source: '7', target: '159' },
              { source: '7', target: '8' },
              { source: '135', target: '141' },
              { source: '200', target: '74' },
              { source: '136', target: '131' },
              { source: '136', target: '134' },
              { source: '136', target: '159' },
              { source: '8', target: '7' },
              { source: '201', target: '200' },
              { source: '201', target: '65' },
              { source: '9', target: '8' },
              { source: '9', target: '7' },
              { source: '74', target: '201' },
              { source: '74', target: '200' },
              { source: '10', target: '3' },
              { source: '10', target: '8' },
              { source: '76', target: '88' },
              { source: '205', target: '142' },
              { source: '141', target: '135' },
              { source: '142', target: '136' },
              { source: '142', target: '130' },
              { source: '211', target: '65' },
              { source: '148', target: '92' },
              { source: '85', target: '105' },
              { source: '149', target: '161' },
              { source: '149', target: '92' },
              { source: '149', target: '148' },
              { source: '151', target: '120' },
              { source: '151', target: '161' },
              { source: '88', target: '85' },
              { source: '88', target: '105' },
              { source: '88', target: '76' },
              { source: '88', target: '54' },
              { source: '92', target: '131' },
              { source: '92', target: '136' },
              { source: '92', target: '169' },
              { source: '92', target: '205' },
              { source: '92', target: '161' },
              { source: '92', target: '142' },
              { source: '92', target: '199' },
              { source: '30', target: '31' },
              { source: '31', target: '30' },
              { source: '159', target: '9' },
              { source: '159', target: '10' },
              { source: '159', target: '151' },
              { source: '159', target: '148' },
              { source: '159', target: '149' },
              { source: '159', target: '161' },
              { source: '159', target: '162' },
              { source: '159', target: '37' },
              { source: '160', target: '7' },
              { source: '160', target: '8' },
              { source: '161', target: '45' },
              { source: '161', target: '178' },
              { source: '162', target: '161' },
              { source: '37', target: '38' },
              { source: '38', target: '170' },
              { source: '105', target: '68' },
              { source: '105', target: '88' },
              { source: '169', target: '92' },
              { source: '170', target: '120' },
              { source: '170', target: '160' },
              { source: '170', target: '121' },
              { source: '45', target: '7' },
              { source: '45', target: '38' },
              { source: '111', target: '105' },
              { source: '112', target: '105' },
              { source: '112', target: '111' },
              { source: '114', target: '105' },
              { source: '114', target: '111' },
              { source: '178', target: '159' },
              { source: '115', target: '112' },
              { source: '115', target: '114' },
              { source: '54', target: '88' },
              { source: '120', target: '170' },
              { source: '121', target: '170' }
            ],
            categories: [
              { name: 'single' },
              { name: 'cycle-15' },
              { name: 'cycle-27' },
              { name: 'cycle-41' },
              { name: 'cycle-116' },
              { name: 'cycle-163' }
            ],
            force: {
              edgeLength: 80,
              repulsion: 80,
              gravity: 0.1
            }
            // edges: webkitDep.links
          }
        ]
      };
      myChart.setOption(option);
    })

  }
  render() { //渲染需要陈放Echart实例的容器元素
    return (<div id = "cd-main"
    style = {{
      width: 600,
        height: 600}}>
    </div>);
  }
}
export default CdPic;
