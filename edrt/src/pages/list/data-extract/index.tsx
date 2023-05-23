import {DownOutlined, PlusOutlined} from '@ant-design/icons';
import {Button, message, Input, Drawer, Dropdown, Menu, Modal} from 'antd';
import React, { useState, useRef } from 'react';
import { PageContainer, FooterToolbar } from '@ant-design/pro-layout';
import type { ProColumns, ActionType } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { ModalForm, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import type { ProDescriptionsItemProps } from '@ant-design/pro-descriptions';
import ProDescriptions from '@ant-design/pro-descriptions';
import type { FormValueType } from './components/UpdateForm';
import UpdateForm from './components/UpdateForm';
import {rule, addRule, updateRule, removeRule, dataExtract} from './service';
import type { TableListItem, TableListPagination } from './data';
import {request} from "umi";
import {BasicListItemDataType} from "@/pages/list/basic-list/data";
/**
 * 添加节点
 *
 * @param fields
 */

const handleAdd = async (fields: TableListItem) => {
  const hide = message.loading('正在添加');

  try {
    await addRule({ ...fields });
    hide();
    message.success('添加成功');
    return true;
  } catch (error) {
    hide();
    message.error('添加失败请重试！');
    return false;
  }
};
/**
 * 更新节点
 *
 * @param fields
 */

const handleUpdate = async (fields: FormValueType, currentRow?: TableListItem) => {
  const hide = message.loading('正在配置');

  try {
    await updateRule({
      ...currentRow,
      ...fields,
    });
    hide();
    message.success('配置成功');
    return true;
  } catch (error) {
    hide();
    message.error('配置失败请重试！');
    return false;
  }
};
/**
 * 删除节点
 *
 * @param selectedRows
 */

const handleRemove = async (selectedRows: TableListItem[]) => {
  const hide = message.loading('正在删除');
  if (!selectedRows) return true;

  try {
    await removeRule({
      key: selectedRows.map((row) => row.key),
    });
    hide();
    message.success('删除成功，即将刷新');
    return true;
  } catch (error) {
    hide();
    message.error('删除失败，请重试');
    return false;
  }
};
const handleDetect = async (record: TableListItem) => {
  const hide = message.loading('正在获取数据');

  try {
    await dataExtract(record);
    hide();
    message.success('获取数据成功');
    return true;
  } catch (error) {
    hide();
    message.error('获取数据失败，请重试！');
    return false;
  }
};
const showEditModal = (item: BasicListItemDataType) => {
  // setVisible(true);
  // setCurrent(item);
};

const deleteItem = (name: string) => {
  // postRun('remove', { name });
};

const editAndDelete = (key: string | number, currentItem: BasicListItemDataType) => {
  if (key === 'edit') showEditModal(currentItem);
  else if (key === 'delete') {
    Modal.confirm({
      title: '删除任务',
      content: '确定删除该任务吗？',
      okText: '确认',
      cancelText: '取消',
      onOk: () => deleteItem(currentItem.name),
    });
  }
};

const MoreBtn: React.FC<{
  item: BasicListItemDataType;
}> = ({ item }) => (
  <Dropdown
    overlay={
      <Menu onClick={({ key }) => editAndDelete(key, item)}>
        <Menu.Item key="edit">下载文件</Menu.Item>
        <Menu.Item key="delete">可视化查看</Menu.Item>
      </Menu>
    }
  >
    <a>
      查看特征结果 <DownOutlined />
    </a>
  </Dropdown>
);


const TableList: React.FC = () => {
  /** 新建窗口的弹窗 */
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  /** 分布更新窗口的弹窗 */

  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [showDetail, setShowDetail] = useState<boolean>(false);
  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<TableListItem>();
  const [selectedRowsState, setSelectedRows] = useState<TableListItem[]>([]);
  /** 国际化配置 */

  const columns: ProColumns<TableListItem>[] = [
    {
      title: '项目名称',
      dataIndex: 'name',
      tip: '项目名称是唯一的 key',
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
    // {
    //   title: 'URL',
    //   dataIndex: 'url',
    //   valueType: 'textarea',
    // },
    {
      title: '项目描述',
      dataIndex: 'desc',
      valueType: 'textarea',
      width: 250,
      valueType: 'text',
      ellipsis: true,
    },
    {
      title: '上次更新时间',
      dataIndex: 'lastUpdateTime',
      valueType: 'textarea',
    },
    {
      title: '上次更新时项目commitID',
      dataIndex: 'lastCommitID',
      valueType: 'textarea',
    },
    {
      title: '是否自动更新',
      width: 130,
      dataIndex: 'isAutoUpdate',
      valueType: 'textarea',
    },
    {
      title: '状态',
      width: 80,
      dataIndex: 'status',
      valueType: 'textarea',
    },
    // {
    //   title: '状态',
    //   dataIndex: 'status',
    //   hideInForm: true,
    //   valueEnum: {
    //     0: {
    //       text: '关闭',
    //       status: 'Default',
    //     },
    //     1: {
    //       text: '运行中',
    //       status: 'Processing',
    //     },
    //     2: {
    //       text: '已上线',
    //       status: 'Success',
    //     },
    //     3: {
    //       text: '异常',
    //       status: 'Error',
    //     },
    //   },
    // },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => [
        <a key="subscribeAlert" onClick={() => {
          handleDetect(record);
        }}>
          特征提取
        </a>,
        // <a key="subscribeAlert" href="https://procomponents.ant.design/">
        //   查看特征结果
        // </a>,
        <MoreBtn key="more" />,
        <a key="subscribeAlert" href="https://procomponents.ant.design/">
          定时任务
        </a>,
      ],
    },
  ];

  return (
    <PageContainer>
      <ProTable<TableListItem, TableListPagination>
        headerTitle="特征提取"
        actionRef={actionRef}
        rowKey="key"
        search={{
          labelWidth: 120,
        }}
        // toolBarRender={() => [
        //   <Button
        //     type="primary"
        //     key="primary"
        //     onClick={() => {
        //       handleModalVisible(true);
        //     }}
        //   >
        //     <PlusOutlined /> 新建
        //   </Button>,
        // ]}
        request={rule}
        columns={columns}
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
                服务调用次数总计 {selectedRowsState.reduce((pre, item) => pre + item.callNo!, 0)} 万
              </span>
            </div>
          }
        >
          <Button
            onClick={async () => {
              await handleRemove(selectedRowsState);
              setSelectedRows([]);
              actionRef.current?.reloadAndRest?.();
            }}
          >
            批量删除
          </Button>
          <Button type="primary">批量审批</Button>
        </FooterToolbar>
      )}
      <ModalForm
        title="新建规则"
        width="400px"
        visible={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value as TableListItem);
          if (success) {
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText
          rules={[
            {
              required: true,
              message: '规则名称为必填项',
            },
          ]}
          width="md"
          name="name"
        />
        <ProFormTextArea width="md" name="desc" />
      </ModalForm>
      <UpdateForm
        onSubmit={async (value) => {
          const success = await handleUpdate(value, currentRow);

          if (success) {
            handleUpdateModalVisible(false);
            setCurrentRow(undefined);

            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalVisible(false);
          setCurrentRow(undefined);
        }}
        updateModalVisible={updateModalVisible}
        values={currentRow || {}}
      />

      <Drawer
        width={600}
        visible={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={false}
      >
        {currentRow?.name && (
          <ProDescriptions<TableListItem>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<TableListItem>[]}
          />
        )}
      </Drawer>
    </PageContainer>
  );
};

export default TableList;
