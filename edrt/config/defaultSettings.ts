import { Settings as LayoutSettings } from '@ant-design/pro-layout';

const Settings: LayoutSettings & {
  pwa?: boolean;
  logo?: string;
} = {
  navTheme: 'light',
  // 拂晓蓝
  primaryColor: '#1890ff',
  layout: 'mix',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: true,
  colorWeak: false,
  title: 'RefactorHelper',
  pwa: false,
  //logo: 'https://user-images.githubusercontent.com/55134178/221520679-f61f90f8-0151-4718-83ec-12631aa3b0c0.png',
  logo: 'https://user-images.githubusercontent.com/55134178/221518463-2ec8caa0-9fa2-4bb7-84df-0b3fda8915f5.png',
  iconfontUrl: '',
};

export default Settings;
