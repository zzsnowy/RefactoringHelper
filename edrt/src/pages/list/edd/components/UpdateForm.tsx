import React from 'react';
import {Button, Form, Input, Modal} from 'antd';
import ProForm, {
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
  StepsForm,
  ProFormRadio,
  ProFormDateTimePicker, ModalForm, ProFormDateRangePicker,
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

    // <Modal
    //   width={640}
    //   bodyStyle={{
    //     padding: '32px 40px 48px',
    //   }}
    //   destroyOnClose
    //   title="演进异味检测定时任务"
    //   visible={props.updateModalVisible}
    //   onCancel={() => {
    //     props.onCancel();
    //   }}
    //   footer={[
    //     <Button key="cancel" onClick={props.onCancel}>取消</Button>,
    //     <Button key="submit" type="primary" onClick={props.onSubmit}>提交</Button>,
    //   ]}
    // >
    //   <Form
    //     onFinish={props.onSubmit}
    //     initialValues={{
    //       name: props.values.name,
    //       url: props.values.url,
    //       supportCount: props.values.supportCount,
    //       confidence: props.values.confidence,
    //     }}
    //   >
    //     <Form.Item
    //       label="项目名称"
    //       name="name"
    //       rules={[
    //         {
    //           message: '请输入规则名称！',
    //         },
    //       ]}
    //     >
    //       <Input/>
    //     </Form.Item>
    //
    //     <Form.Item
    //       label="supportCount"
    //       name="supportCount"
    //       rules={[
    //         {
    //
    //           message: '请配置supportCount阈值！',
    //         },
    //       ]}
    //     >
    //       <Input/>
    //     </Form.Item>
    //
    //     <Form.Item
    //       label="confidence"
    //       name="confidence"
    //       rules={[
    //         {
    //
    //           message: '请配置supportCount阈值！',
    //         },
    //       ]}
    //     >
    //       <Input/>
    //     </Form.Item>
    //
    //     <Form.Item
    //       label="触发周期"
    //       name="cron"
    //       rules={[
    //         {
    //           required: true,
    //           message: '请配置confidence阈值！',
    //         },
    //       ]}
    //     >
    //       <Input/>
    //     </Form.Item>
    //
    //   </Form>
    // </Modal>

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
            title="演进异味检测阈值配置"
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
              message: '请输入至少五个字符的规则描述！',
              min: 5,
            },
          ]}
          disabled
        />
      </StepsForm.StepForm>
      <StepsForm.StepForm
        initialValues={{
          supportCount: props.values.supportCount,
        }}
        title="supportCount阈值"
      >
        <ProFormText
          name="supportCount"
          label="配置supportCount"
          width="md"
          rules={[
            {
              required: true,
              message: '请配置supportCount阈值！',
            },
          ]}
        />

      </StepsForm.StepForm>
      <StepsForm.StepForm
        initialValues={{
          confidence: props.values.confidence,
        }}
        title="confidence阈值"
      >
        <ProFormText
          name="confidence"
          label="配置confidence"
          width="md"
          rules={[
            {
              required: true,
              message: '请配置confidence阈值！',
            },
          ]}
        />
      </StepsForm.StepForm>
    </StepsForm>
  );
};

export default UpdateForm;
