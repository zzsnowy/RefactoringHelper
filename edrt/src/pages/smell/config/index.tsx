import React, {useRef, useState} from 'react';
import type {FormInstance} from 'antd';
import {Card, Result, Button, Descriptions, Divider, Alert, Statistic} from 'antd';
import {PageContainer} from '@ant-design/pro-layout';
import ProForm, {ProFormDigit, ProFormSelect, ProFormText, StepsForm} from '@ant-design/pro-form';
import styles from './style.less';

interface StepDataType {
  // payAccount: string;
  // receiverAccount: string;
  // receiverName: string;
  // amount: string;
  // receiverMode: string;

  supportCount: number;
  confidence: number;
  thAdnFac: number;
  thEdnFac: number;
  thBalanceFac: number;
  thUdn: number;

}

const StepDescriptions: React.FC<{
  stepData: StepDataType;
  bordered?: boolean;
}> = ({stepData, bordered}) => {
  const {supportCount, confidence, thAdnFac, thEdnFac, thBalanceFac, thUdn} = stepData;
  return (
    <Descriptions column={1} bordered={bordered}>
      <Descriptions.Item label="支持计数（演进依赖）"> {supportCount}</Descriptions.Item>
      <Descriptions.Item label="置信度（演进依赖）"> {confidence}</Descriptions.Item>
      <Descriptions.Item label="传入依赖阈值因子（枢纽型依赖）"> {thAdnFac}</Descriptions.Item>
      <Descriptions.Item label="传出依赖阈值因子（枢纽型依赖）"> {thEdnFac}</Descriptions.Item>
      <Descriptions.Item label="平衡阈值因子（枢纽型依赖）"> {thBalanceFac}</Descriptions.Item>
      <Descriptions.Item label="不稳定依赖阈值（不稳定依赖）"> {thUdn}</Descriptions.Item>

      {/*<Descriptions.Item label="转账金额">*/}
      {/*  <Statistic*/}
      {/*    value={amount}*/}
      {/*    suffix={*/}
      {/*      <span*/}
      {/*        style={{*/}
      {/*          fontSize: 14,*/}
      {/*        }}*/}
      {/*      >*/}
      {/*        元*/}
      {/*      </span>*/}
      {/*    }*/}
      {/*    precision={2}*/}
      {/*  />*/}
      {/*</Descriptions.Item>*/}
    </Descriptions>
  );
};

const StepResult: React.FC<{
  onFinish: () => Promise<void>;
}> = (props) => {
  return (
    <Result
      status="success"
      title="操作成功"
      subTitle="系统即将开始扫描"
      extra={
        <>
          {/*<Button type="primary" onClick={props.onFinish}>*/}
          {/*  */}
          {/*</Button>*/}
          <Button>扫描详情</Button>
        </>
      }
      className={styles.result}
    >
      {props.children}
    </Result>
  );
};

const StepForm: React.FC<Record<string, any>> = () => {
  const [stepData, setStepData] = useState<StepDataType>({
    // payAccount: 'ant-design@alipay.com',
    // receiverAccount: 'test@example.com',
    // receiverName: 'Alex',
    // amount: '500',
    // receiverMode: 'alipay',

    supportCount: 2,
    confidence: 0.5,
    thAdnFac: 0.5,
    thEdnFac: 0.5,
    thBalanceFac: 0.25,
    thUdn: 0.3,
  });
  const [current, setCurrent] = useState(0);
  const formRef = useRef<FormInstance>();

  return (
    <PageContainer content="配置异味定义，扫描项目异味">
      <Card bordered={false}>
        <StepsForm
          current={current}
          onCurrentChange={setCurrent}
          submitter={{
            render: (props, dom) => {
              if (props.step === 3) {
                return null;
              }
              return dom;
            },
          }}
        >
          <StepsForm.StepForm<StepDataType>
            formRef={formRef}
            title="演进依赖阈值配置"
            initialValues={stepData}
            onFinish={async (values) => {
              setStepData(values);
              return true;
            }}
          >
            <ProFormText
              label="支持计数阈值"
              placeholder={"默认为2"}
              width="md"
              name="supportCount"
              rules={[{required: true, message: '请输入支持计数阈值'}]}
            />
            <ProFormText
              label="置信度"
              placeholder={"默认为0.5"}
              width={"md"}
              name={"confidence"}
              rules={[{required: true, message: '请输入置信度'}]}
            />
          </StepsForm.StepForm>

          <StepsForm.StepForm title="枢纽型依赖、不稳定依赖阈值配置" initialValues={stepData}
                              onFinish={async (values) => {
                                setStepData({...stepData, ...values});
                                return true;
                              }}>
            <div className={styles.result}>
              <Alert
                closable
                showIcon
                message="根据需要自定义阈值。"
                style={{marginBottom: 24}}
              />
              {/*<StepDescriptions stepData={stepData} bordered/>*/}
              <Divider style={{margin: '24px 0'}}/>
              <ProFormText
                label="传入依赖阈值因子"
                width="md"
                name="thAdnFac"
                placeholder={"默认为1/2，即取中位数"}
                rules={[{required: true, message: '请输入传入依赖阈值因子'}]}
              />
              <ProFormText
                label="传出依赖阈值因子"
                width="md"
                name="thEdnFac"
                placeholder={"默认为1/2，即取中位数"}
                rules={[{required: true, message: '请输入传出依赖阈值因子'}]}
              />
              <ProFormText
                label="平衡阈值因子"
                width="md"
                name="thBalanceFac"
                placeholder={"默认为1/4"}
                rules={[{required: true, message: '请输入平衡阈值因子'}]}
              />
              <ProFormText
                label="不稳定依赖阈值"
                width="md"
                name="thUdn"
                placeholder={"默认为0.3"}
                rules={[{required: true, message: '请输入不稳定依赖阈值'}]}
              />
            </div>
          </StepsForm.StepForm>
          <StepsForm.StepForm title = "项目配置" onFinish={async (values) => {
            setStepData({...stepData, ...values});
            return true;
          }}>

          </StepsForm.StepForm>
          <StepsForm.StepForm title="完成">
            <StepResult
              onFinish={async () => {
                setCurrent(0);
                formRef.current?.resetFields();
              }}
            >
              <StepDescriptions stepData={stepData}/>
            </StepResult>
          </StepsForm.StepForm>
        </StepsForm>
        <Divider style={{margin: '40px 0 24px'}}/>
        {/*<div className={styles.desc}>*/}
        {/*  <h3>说明</h3>*/}
        {/*  <h4>转账到支付宝账户</h4>*/}
        {/*  <p>*/}
        {/*    如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。*/}
        {/*  </p>*/}
        {/*  <h4>转账到银行卡</h4>*/}
        {/*  <p>*/}
        {/*    如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。*/}
        {/*  </p>*/}
        {/*</div>*/}
      </Card>
    </PageContainer>
  );
};

export default StepForm;
