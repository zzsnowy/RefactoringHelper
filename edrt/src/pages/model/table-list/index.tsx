import { CloseCircleOutlined } from '@ant-design/icons';
import { Card, Col, Popover, Row, message, Space, Tag } from 'antd';

import type { FC } from 'react';
import { useState } from 'react';
import ProForm, { ProFormCheckbox, ProFormDatePicker } from '@ant-design/pro-form';
import type { ProColumnType } from '@ant-design/pro-table';
import { EditableProTable } from '@ant-design/pro-table';
import { PageContainer, FooterToolbar } from '@ant-design/pro-layout';
import styles from './style.less';
import { ProFormSegmented, CheckCard } from '@ant-design/pro-components';

interface TableFormDateType {
  key: string;
  name?: string;
  url?: string;
  syncTime?: number;
  commitId?: string;
}
type InternalNamePath = (string | number)[];

const fieldLabels = {
  name: '仓库名',
  url: '仓库域名',
  owner: '仓库管理员',
  approver: '审批人',
  dateRange: '生效日期',
  type: '仓库类型',
  name2: '任务名',
  url2: '任务描述',
  owner2: '执行人',
  approver2: '责任人',
  dateRange2: '生效日期',
  type2: '任务类型',

  modelType: '训练算法',
  refactorOpt: '目标重构操作',
};

const rfParam = {
  n_estimators: 40,
  min_samples_split: 6,
  min_samples_leaf: 1,
  max_depth: 30,
  criterion: 'entropy',
};

const tableData: TableFormDateType[] = [
  {
    key: '1',
    name: 'zuul',
    url: 'https://github.com/Netflix/zuul',
    syncTime: 1682189447000,
    commitId: 'master',
  },
  // {
  //   key: '2',
  //   name: 'litemall',
  //   url: 'https://github.com/linlinjava/litemall',
  //   syncTime: 1682189447000,
  //   commitId: 'dev'
  // },
  // {
  //   key: '3',
  //   name: 'apollo',
  //   url: 'https://github.com/apolloconfig/apollo',
  //   syncTime: 1682189447000,
  //   commitId: 'master'
  // },
];

interface ErrorField {
  name: InternalNamePath;
  errors: string[];
}

const AdvancedForm: FC<Record<string, any>> = () => {
  const [error, setError] = useState<ErrorField[]>([]);
  const getErrorInfo = (errors: ErrorField[]) => {
    const errorCount = errors.filter((item) => item.errors.length > 0).length;
    if (!errors || errorCount === 0) {
      return null;
    }
    const scrollToField = (fieldKey: string) => {
      const labelNode = document.querySelector(`label[for="${fieldKey}"]`);
      if (labelNode) {
        labelNode.scrollIntoView(true);
      }
    };
    const errorList = errors.map((err) => {
      if (!err || err.errors.length === 0) {
        return null;
      }
      const key = err.name[0] as string;
      return (
        <li key={key} className={styles.errorListItem} onClick={() => scrollToField(key)}>
          <CloseCircleOutlined className={styles.errorIcon} />
          <div className={styles.errorMessage}>{err.errors[0]}</div>
          <div className={styles.errorField}>{fieldLabels[key]}</div>
        </li>
      );
    });
    return (
      <span className={styles.errorIcon}>
        <Popover
          title="表单校验信息"
          content={errorList}
          overlayClassName={styles.errorPopover}
          trigger="click"
          getPopupContainer={(trigger: HTMLElement) => {
            if (trigger && trigger.parentNode) {
              return trigger.parentNode as HTMLElement;
            }
            return trigger;
          }}
        >
          <CloseCircleOutlined />
        </Popover>
        {errorCount}
      </span>
    );
  };

  const onFinish = async (values: Record<string, any>) => {
    setError([]);
    try {
      // await fakeSubmitForm(values);
      message.success('提交成功');
    } catch {
      // console.log
    }
  };

  const onFinishFailed = (errorInfo: any) => {
    setError(errorInfo.errorFields);
  };

  const columns: ProColumnType<TableFormDateType>[] = [
    {
      title: '项目名',
      dataIndex: 'name',
      key: 'name',
      width: '10%',
    },
    {
      title: '项目URL',
      dataIndex: 'url',
      key: 'url',
      width: '30%',
      render: (url) => {
        return <a>{url}</a>;
      },
    },
    {
      title: '最近同步时间',
      dataIndex: 'syncTime',
      key: 'syncTime',
      valueType: 'dateTime',
      width: '20%',
    },
    {
      title: 'Git节点Id',
      dataIndex: 'commitId',
      key: 'commitId',
      render: (commitId) => {
        return <Tag color={'green'}>{commitId}</Tag>;
      },
    },
    {
      title: '操作',
      key: 'action',
      valueType: 'option',
      render: (_, record: TableFormDateType, index, action) => {
        return [
          <a
            key="eidit"
            onClick={() => {
              action?.startEditable(record.key);
            }}
          >
            编辑
          </a>,
        ];
      },
    },
  ];

  return (
    <ProForm
      layout="vertical"
      hideRequiredMark
      submitter={{
        render: (props, dom) => {
          return (
            <></>
            // <FooterToolbar>
            //   {getErrorInfo(error)}
            //   {dom}
            // </FooterToolbar>
          );
        },
      }}
      initialValues={{ members: tableData }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
    >
      <PageContainer content="自定义训练重构模型">
        <Card title={'选择训练算法'} className={styles.card} bordered={false}>
          <CheckCard.Group
            onChange={(value) => {
              console.log('value', value);
            }}
            defaultValue="A"
          >
            <CheckCard
              avatar={'https://cdn-icons-png.flaticon.com/128/2346/2346704.png'}
              title={'RF（随机森林）'}
              description="一种基于决策树的集成学习方法，能够处理大量特征和数据，同时具有很好的鲁棒性和准确性"
              value="A"
            />
            <CheckCard
              avatar={'https://cdn-icons-png.flaticon.com/128/2961/2961232.png'}
              title="DT（决策树）"
              description="一种简单但强大的分类器，能够处理离散和连续型特征，同时易于解释和理解"
              value="B"
            />
            <CheckCard
              avatar={
                'https://cdn2.iconfinder.com/data/icons/artificial-intelligence-169/32/svm_support_vector_machine_machine_learning_classification_artificial_intelligence-512.png'
              }
              title="SVM（支持向量机）"
              description="一种强大的分类器，能够高效地处理高维度数据，同时具有良好的泛化性能"
              value="C"
            />
            <br />
            <CheckCard
              avatar={
                'https://as2.ftcdn.net/v2/jpg/05/63/21/69/1000_F_563216945_LV5n1DtrCHPgGyY6GV6qxmJ8B7PELkGg.jpg'
              }
              title="LightGBM"
              description="一个基于梯度提升决策树的轻量级、高效率的算法，它的主要特点是采用了基于直方图……"
              value="D"
            />
            <CheckCard
              avatar={
                'https://as1.ftcdn.net/v2/jpg/02/76/96/12/1000_F_276961214_3VuZ4j5YqHgH1fBsvWwQSwMpllQdt8Pm.jpg'
              }
              title="XGBoost"
              description="一种基于梯度提升决策树的算法，通过迭代地训练多个决策树模型，每次迭代都会根据上……"
              value="E"
            />
            <CheckCard
              avatar={
                'https://t4.ftcdn.net/jpg/04/30/62/25/240_F_430622503_qeLY92q4pOS5xm6zn5jmNSuSqvoiX9aE.jpg'
              }
              title="K-Nearest Neighbors"
              description="一种基于实例的分类器，能够对未知数据进行快速分类，同时对于不平衡数据集具有较好的表现"
              value="F"
            />
          </CheckCard.Group>
        </Card>
        <Card title="配置训练参数" className={styles.card} bordered={false}>
          <Row gutter={16}>
            <Col lg={6} md={12} sm={24}>
              <ProFormCheckbox.Group
                options={[
                  {
                    value: '1',
                    label: '移动方法',
                  },
                  {
                    value: '2',
                    label: '移动类',
                  },
                  {
                    value: '3',
                    label: '提取方法',
                  },
                  {
                    value: '4',
                    label: '提取类',
                  },
                ]}
                layout={'horizontal'}
                label={fieldLabels.refactorOpt}
                name="publicType"
              />
            </Col>
            <Col xl={{ span: 6, offset: 2 }} lg={{ span: 8 }} md={{ span: 12 }} sm={24}>
              <ProFormDatePicker
                label={'开始训练时间'}
                fieldProps={{
                  style: {
                    width: '100%',
                  },
                }}
                rules={[{ required: true, message: '请选择运行时间' }]}
              ></ProFormDatePicker>
            </Col>
            <Col xl={{ span: 8, offset: 2 }} lg={{ span: 10 }} md={{ span: 24 }} sm={24}>
              <ProFormSegmented
                name="segmented"
                label="训练结束自动发布"
                valueEnum={{
                  open: '不发布',
                  closed: '发布',
                }}
              />
            </Col>
          </Row>
        </Card>
        <Card title="配置训练项目" bordered={false}>
          <ProForm.Item name="members">
            <EditableProTable<TableFormDateType>
              recordCreatorProps={{
                record: () => {
                  return {
                    key: `0${Date.now()}`,
                  };
                },
              }}
              columns={columns}
              rowKey="key"
            />
          </ProForm.Item>
        </Card>
      </PageContainer>
    </ProForm>
  );
};

export default AdvancedForm;
