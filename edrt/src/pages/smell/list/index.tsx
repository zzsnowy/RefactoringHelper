import {PlusOutlined, UploadOutlined} from '@ant-design/icons';
import {Button, Card, Drawer, Input, message, Modal, Progress, Space, Switch, Table, Tag} from 'antd';
import React, {useState, useRef, useEffect, ReactText} from 'react';
import {PageContainer, FooterToolbar} from '@ant-design/pro-layout';
import type {ProColumns, ActionType} from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
// import { ModalForm, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import type {ProDescriptionsItemProps} from '@ant-design/pro-descriptions';
import ProDescriptions from '@ant-design/pro-descriptions';
import {useHistory} from "react-router";
import { Line } from '@ant-design/charts';
import {ChartCard} from "@/pages/dashboard/analysis/components/Charts";

import { Form, Upload } from 'antd';
import type { UploadProps } from 'antd';
import CdPic from "@/pages/smell/list/cdPic";



// const BomUpload: React.FC = (props) => {
//   const uploadProps: UploadProps = {
//     name: 'bom',
//     action: file => {
//       const formData = new FormData();
//       formData.append('project', props.projectId);
//       formData.append('bom', file);
//       uploadBom(formData)
//     },
//     headers: {
//       authorization: 'authorization-text',
//     },
//     onChange(info) {
//       if (info.file.status !== 'uploading') {
//         console.log(info.file, info.fileList);
//       }
//       if (info.file.status === 'done') {
//         message.success(`${info.file.name} file uploaded successfully`);
//       } else if (info.file.status === 'error') {
//         message.error(`${info.file.name} file upload failed.`);
//       }
//     },
//   };
//   return (
//     <Upload {...uploadProps}>
//       <Button icon={<UploadOutlined />}>上传项目物料清单</Button>
//     </Upload>)
// };

// const ProjectUpload: React.FC = (props) => {
//   const uploadProps: UploadProps = {
//     name: 'file',
//     action: file => {
//       const formData = new FormData();
//       formData.append('file', file);
//       uploadProjectFile(formData)
//         .then((resp) => {
//           console.log(resp)
//           let link = document.createElement('a');
//           link.href = window.URL.createObjectURL(resp.data);
//           link.download = 'bom.json';
//           //此写法兼容可火狐浏览器
//           document.body.appendChild(link);
//           let evt = document.createEvent("MouseEvents");
//           evt.initEvent("click", false, false);
//           link.dispatchEvent(evt);
//           document.body.removeChild(link);
//         })
//     },
//     headers: {
//       authorization: 'authorization-text',
//     },
//     onChange(info) {
//       if (info.file.status !== 'uploading') {
//         console.log(info.file, info.fileList);
//       }
//       if (info.file.status === 'done') {
//         message.success(`${info.file.name} file uploaded successfully`);
//       } else if (info.file.status === 'error') {
//         message.error(`${info.file.name} file upload failed.`);
//       }
//     },
//   };
//   return (
//     <Upload {...uploadProps}>
//       <Button icon={<UploadOutlined />}>项目文件扫描生成SBOM</Button>
//     </Upload>)
// }


// const ViolationList = (props) => {
//   const columns = [
//     {
//       title: '违反策略名称',
//       dataIndex: 'policy',
//       key: 'policy',
//       render: text => <a>{text}</a>,
//     },
//     {
//       title: '违反策略条件',
//       dataIndex: 'condition',
//       key: 'condition',
//       render: (condition: PolicyCondition) => {
//         return <>
//           <Tag>{condition.subject}</Tag>
//           {/*<Tag>{condition.operator}</Tag>*/}
//           {/*<Tag>{condition.value}</Tag>*/}
//         </>
//       }
//     },
//     {
//       title: '类别',
//       dataIndex: 'type',
//       key: 'type',
//       render: text => <Tag>{text}</Tag>
//     },
//     {
//       title: '违规组件名称',
//       dataIndex: 'componentName',
//       render: text => (<Tag color={"green"}>{text}</Tag>)
//     },
//   ];
//   type Item = { policy: string, condition: PolicyCondition, type: string, componentName: string }
//
//   const [data, setData] = useState<Item[]>([])
//   const [dataTotal, setDataTotal] = useState<number>(0)
//   console.log('lljjjjjjj')
//   useEffect(() => {
//     getPolicyViolation(props.projectId)
//       .then(({total, data}) => {
//         const allData = []
//         for (const item of data) {
//           const newItem: Item = {
//             policy: item.policyCondition.policy.name,
//             condition: item.policyCondition,
//             "type": {'LICENSE': '许可证', 'SECURITY': '安全违规', 'OPERATIONAL':'CPE'}[item.type],
//             componentName: item.component.name
//           }
//           allData.push(newItem)
//         }
//         setData(allData)
//         setDataTotal(allData.length)
//       })
//   }, [])
//
//   return (<div>
//         <Table columns={columns} dataSource={data} />
//   </div>)
//
// }


type SmellItem = {
  username: string // 创建者
  projectName: string
  lastScanAt: number // 最后扫描时间
  metric: SmellMetric
}
type SmellMetric = {
  ed: number // 演进依赖个数
  hd: number // 枢纽型依赖个数
  ud: number // 不稳定依赖个数
  cd: number // 环依赖个数
}

const testData: SmellItem[] = [
  {
    username: '张三',
    projectName: 'id-meta',
    lastScanAt: 1682154663000,
    metric: {
      ed: 5,
      hd: 10,
      ud: 30,
      cd: 5
    }
  },
  {
    username: '李四',
    projectName: 'zuul-pro',
    lastScanAt: 1682154663000,
    metric: {
      ed: 106,
      hd: 39,
      ud: 76,
      cd: 39
    }
  },
  {
    username: '张三',
    projectName: 'nju-soft-ding',
    lastScanAt: 1682153663000,
    metric: {
      ed: 4,
      hd: 6,
      ud: 1,
      cd: 0
    }
  },
  {
    username: '李四',
    projectName: 'eureka-pro',
    lastScanAt: 1682154263000,
    metric: {
      ed: 7,
      hd: 29,
      ud: 36,
      cd: 1
    }
  }
]

const SmellList: React.FC = () => {
  /** 新建窗口的弹窗 */
  // const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  /** 分布更新窗口的弹窗 */

    // const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [showDetail, setShowDetail] = useState<boolean>(false);
  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<SmellItem>();
  const [selectedRowsState, setSelectedRows] = useState<SmellItem[]>([]);
  const history = useHistory();
  const [cdVis, setCdVis] = useState<boolean>(false);
  const showCdPic = () => {
    setCdVis(true);
  }
  /** 国际化配置 */

  const columns: ProColumns<SmellItem>[] = [
    {
      title: '项目名称',
      dataIndex: 'projectName',
      sorter: true,
      render: (dom, entity) => {
        return (
          <a
            onClick={() => {
              setCurrentRow(entity);
              setShowDetail(true);
            }}
          >
            {dom}
          </a>
        );
      },
    },
    {
      title: '创建者',
      dataIndex: 'username',
      tip: 'SPDX 制定的许可证集合',
    },
    {
      title: '最后扫描时间',
      dataIndex: 'lastScanAt',
      valueType: 'dateTime'
      // valueEnum: {}
    },
    {
      title: '演进依赖个数',
      dataIndex: ['metric', 'ed'],
      render: (num: number) => (<Tag color={"volcano"}>{num}</Tag>)
    },
    {
      title: '环依赖个数',
      dataIndex: ['metric', 'cd'],
      render: (num: number) => (<Space><Tag color={"orange"}>{num}</Tag><a onClick={showCdPic}>图表</a></Space>)
    },
    {
      title: '枢纽型依赖',
      dataIndex: ['metric', 'hd'],
      render: (num: number) => (<Tag color={"green"}>{num}</Tag>)
    },
    {
      title: '不稳定依赖',
      dataIndex: ['metric', 'ud'],
      render: (num: number) => (<Tag color={"blue"}>{num}</Tag>)
    },
    {
      title: '操作',
      render: () => (<Space><Button>详情</Button><br/><Button>删除</Button></Space>)
    }
  ];
  const detailsColumns: ProColumns<SmellItem>[] = [
    ...columns,
  ]

  // const [data, setData] = useState([]);
  //
  // const config = {
  //   data,
  //   xField: 'date',
  //   yField: 'value',
  //   seriesField: 'category',
  //   width: 1100,
  //   height: 200,
  //   color: ['#1979C9', '#D62A0D', '#FAA219', "#ff1919", '#AA1243'],
  // };

  return (
    <PageContainer>
      {/*<ChartCard*/}
      {/*  title={'异味扫描'}*/}

      {/*>*/}
      {/*  <Line {...config} />*/}
      {/*</ChartCard>*/}
      <ProTable<SmellItem>
        headerTitle="异味扫描结果列表"
        actionRef={actionRef}
        rowKey="uuid"
        dataSource={testData}
        search={false}
        toolBarRender={() => [
          // <ProjectUpload/>,
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              // handleModalVisible(true);
              history.push('/project/create')
            }}
          >
            <PlusOutlined/> 新建
          </Button>,
        ]}
        // request={project}
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => {
            console.log('llj -> ' + selectedRows.length)
            setSelectedRows(selectedRows);
          },
        }}
      />
      {selectedRowsState?.length > 0 && (
        <FooterToolbar
          extra={
            <div>
              已选择{' '}
              <a
                style={{
                  fontWeight: 600,
                }}
              >
                {selectedRowsState.length}
              </a>{' '}
              项 &nbsp;&nbsp;
              <span>
                已选许可证{selectedRowsState.length}个
                {/*服务调用次数总计 {selectedRowsState.reduce((pre, item) => pre + item.callNo!, 0)} 万*/}
              </span>
            </div>
          }
        >
          <Button
            type="primary"
            onClick={async () => {
              // selectedRowsState.map(proj => delProject(proj.uuid))
              setSelectedRows([])
              actionRef.current?.reloadAndRest?.()
            }}
          >批量删除</Button>
        </FooterToolbar>
      )}
      <Modal
        title={'环依赖异味图'}
        open={cdVis}
        onCancel={() => setCdVis(false)}
        width={700}
      >
        <CdPic></CdPic>
      </Modal>


      <Drawer
        width={600}
        visible={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={true}
      >
        {/*{currentRow?.name && (<BomUpload projectId={currentRow.uuid}/>)}*/}
        {currentRow?.name && (
          <ProDescriptions<SmellItem>
            column={1}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={detailsColumns as ProDescriptionsItemProps<SmellItem>[]}
            layout={"horizontal"}
          />
        )}
        {/*{currentRow?.name && (<ViolationList projectId={currentRow.uuid}/>)}*/}
      </Drawer>
    </PageContainer>
  );
};

export default SmellList;

