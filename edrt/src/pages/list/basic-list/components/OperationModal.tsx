import type { FC } from 'react';
import {
  ModalForm,
  ProFormSelect,
  ProFormDateTimePicker,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-form';
import type { BasicListItemDataType } from '../data';
import styles from '../style.less';
import { Button, Result } from 'antd';

type OperationModalProps = {
  done: boolean;
  visible: boolean;
  current: Partial<BasicListItemDataType> | undefined;
  onDone: () => void;
  onSubmit: (values: BasicListItemDataType) => void;
};

const OperationModal: FC<OperationModalProps> = (props) => {
  const { done, visible, current, onDone, onSubmit, children } = props;
  if (!visible) {
    return null;
  }
  return (
    <ModalForm<BasicListItemDataType>
      visible={visible}
      title={done ? null : `项目${current ? '导入' : '导入'}`}
      className={styles.standardListForm}
      width={640}
      onFinish={async (values) => {
        onSubmit(values);
      }}
      initialValues={current}
      submitter={{
        render: (_, dom) => (done ? null : dom),
      }}
      trigger={<>{children}</>}
      modalProps={{
        onCancel: () => onDone(),
        destroyOnClose: true,
        bodyStyle: done ? { padding: '72px 0' } : {},
      }}
    >
      {!done ? (
        <>
          <ProFormText
            name="name"
            label="项目名称"
            rules={[{ required: true, message: '请输入项目名称' }]}
            placeholder="请输入"
          />
          {/*<ProFormDateTimePicker*/}
          {/*  name="createdAt"*/}
          {/*  label="开始时间"*/}
          {/*  rules={[{ required: true, message: '请选择开始时间' }]}*/}
          {/*  fieldProps={{*/}
          {/*    style: {*/}
          {/*      width: '100%',*/}
          {/*    },*/}
          {/*  }}*/}
          {/*  placeholder="请选择"*/}
          {/*/>*/}
          {/*<ProFormSelect*/}
          {/*  name="owner"*/}
          {/*  label="任务负责人"*/}
          {/*  rules={[{ required: true, message: '请选择任务负责人' }]}*/}
          {/*  options={[*/}
          {/*    {*/}
          {/*      label: '付晓晓',*/}
          {/*      value: 'xiao',*/}
          {/*    },*/}
          {/*    {*/}
          {/*      label: '周毛毛',*/}
          {/*      value: 'mao',*/}
          {/*    },*/}
          {/*  ]}*/}
          {/*  placeholder="请选择管理员"*/}
          {/*/>*/}
          <ProFormTextArea
            name="url"
            label="项目代码仓地址"
            rules={[{ required: true, message: '请输入完整的项目地址，例如：https://github.com/nickname/edrt'}]}
            placeholder="请输入完整的项目地址，例如：https://github.com/nickname/edrt"
          />
          <ProFormTextArea
            name="desc"
            label="项目描述"
            rules={[{ message: '请输入项目描述' }]}
            placeholder="简单介绍下项目吧～"
          />
        </>
      ) : (
        <Result
          status="success"
          title="操作成功"
          subTitle="请点击下面按钮，继续您的操作！"
          extra={
            <Button type="primary" onClick={onDone}>
              知道了
            </Button>
          }
          className={styles.formResult}
        />
      )}
    </ModalForm>
  );
};

export default OperationModal;
