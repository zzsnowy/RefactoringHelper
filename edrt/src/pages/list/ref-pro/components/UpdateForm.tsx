import React from 'react';
import { Modal } from 'antd';
import {
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
  StepsForm,
  ProFormRadio,
  ProFormDateTimePicker,
} from '@ant-design/pro-form';
import type { TableListItem } from '../data';

export type FormValueType = {
  target?: string;
  template?: string;
  type?: string;
  time?: string;
  frequency?: string;
} & Partial<TableListItem>;

export type UpdateFormProps = {
  onCancel: (flag?: boolean, formVals?: FormValueType) => void;
  onSubmit: (values: FormValueType) => Promise<void>;
  updateModalVisible: boolean;
  values: Partial<TableListItem>;
};

const UpdateForm: React.FC<UpdateFormProps> = (props) => {
  return (
    <StepsForm
      stepsProps={{
        size: 'small',
      }}
      stepsFormRender={(dom, submitter) => {
        return (
          <Modal
            width={640}
            bodyStyle={{
              padding: '32px 40px 48px',
            }}
            destroyOnClose
            title="规则配置"
            visible={props.updateModalVisible}
            footer={submitter}
            onCancel={() => {
              props.onCancel();
            }}
          >
            {dom}
          </Modal>
        );
      }}
      onFinish={props.onSubmit}
    >
      <StepsForm.StepForm
        initialValues={{
          name: props.values.name,
          url: props.values.url,
        }}
        title="基本信息"
      >
        <ProFormText
          name="name"
          label="项目名称"
          width="md"
          rules={[
            {
              required: true,
              message: '请输入规则名称！',
            },
          ]}
          disabled
        />
        <ProFormTextArea
          name="url"
          width="md"
          label="项目代码仓地址"
          placeholder="请输入至少五个字符"
          rules={[
            {
              required: true,
              message: '请输入完整的项目代码仓地址！',
              min: 5,
            },
          ]}
          disabled
        />
      </StepsForm.StepForm>
    </StepsForm>
  );
};

export default UpdateForm;
