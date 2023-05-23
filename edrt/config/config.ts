// https://umijs.org/config/
import { defineConfig } from 'umi';
import { join } from 'path';
import defaultSettings from './defaultSettings';
import proxy from './proxy';

const { REACT_APP_ENV } = process.env;

export default defineConfig({
  hash: true,
  antd: {},
  dva: {
    hmr: true,
  },
  layout: {
    // https://umijs.org/zh-CN/plugins/plugin-layout
    locale: true,
    siderWidth: 208,
    ...defaultSettings,
  },
  // https://umijs.org/zh-CN/plugins/plugin-locale
  locale: {
    // default zh-CN
    default: 'zh-CN',
    antd: true,
    // default true, when it is true, will use `navigator.language` overwrite default
    baseNavigator: true,
  },
  dynamicImport: {
    loading: '@ant-design/pro-layout/es/PageLoading',
  },
  targets: {
    ie: 11,
  },
  // umi routes: https://umijs.org/docs/routing
  routes: [
    {
      path: '/user',
      layout: false,
      routes: [
        {
          path: '/user/login',
          layout: false,
          name: 'login',
          component: './user/Login',
        },
        {
          path: '/user',
          redirect: '/user/login',
        },
        {
          name: 'register-result',
          icon: 'smile',
          path: '/user/register-result',
          component: './user/register-result',
        },
        {
          name: 'register',
          icon: 'smile',
          path: '/user/register',
          component: './user/register',
        },
        {
          component: '404',
        },
      ],
    },
    // {
    //   path: '/dashboard',
    //   name: 'dashboard',
    //   icon: 'dashboard',
    //   routes: [
    //     {
    //       path: '/dashboard',
    //       redirect: '/dashboard/analysis',
    //     },
    //     {
    //       name: 'monitor',
    //       icon: 'smile',
    //       path: '/dashboard/monitor',
    //       component: './dashboard/monitor',
    //     },
    //     {
    //       name: 'workplace',
    //       icon: 'smile',
    //       path: '/dashboard/workplace',
    //       component: './dashboard/workplace',
    //     },
    //   ],
    // },
    // {
    //   path: '/basic-list',
    //   icon: 'folder-open',
    //   name: '项目管理',
    //   component: './list/basic-list',
    // },
    // {
    //   name: '演进异味检测',
    //   icon: 'alert',
    //   path: '/list/edd',
    //   component: './list/edd',
    // },
    // {
    //   name: '特征提取',
    //   icon: 'pie-chart',
    //   path: '/list/data-extract',
    //   component: './list/data-extract',
    // },
    // {
    //   name: '样本生成',
    //   icon: 'dot-chart',
    //   path: '/list/table-list',
    //   component: './list/table-list',
    // },
    {
      name: '异味管理',
      icon: 'alert',
      path: '/smell',
      routes: [
        {
          path: '/smell',
          redirect: '/smell/list',
        },
        {
          name: '异味配置',
          path: '/smell/config',
          component: './smell/config'
        },
        {
          name: '异味可视化',
          path: '/smell/list',
          component: './smell/list',
        }
      ]
    },
    {
      name: '模型训练',
      icon: 'experiment',
      path: '/model',
      routes: [
        {
          path: '/model',
          redirect: '/model/table-list',
        },
        {
          name: '模型训练',
          icon: 'smile',
          path: '/model/table-list',
          component: './model/table-list',
        },
        {
          name: '我的模型',
          icon: 'dot-chart',
          path: '/model/mymodel',
          component: './model/mymodel',
        },
      ],
    },
    // {
    //   name: '重构推荐',
    //   icon: 'build',
    //   path: '/list/refactoring-recommend',
    //   component: './list/refactoring-recommend',
    // },
    {
      name: '重构推荐',
      icon: 'build',
      path: '/center',
      component: './center',
    },
    {
      name: '定时任务管理',
      icon: 'ClockCircleOutlined',
      path: '/task'
    },
    {
      name: '系统管理',
      icon: 'user',
      routes: [
        {
          name: '项目管理',
          icon: 'folder-open',
        },
        {
          name: '团队管理',
          icon: 'area-chart',
        },
        {
          name: '用户管理',
        }
      ]
    },
    // {
    //   name: '团队管理',
    //   icon: 'area-chart',
    //   path: '/list/workplace',
    //   component: './list/workplace',
    // },
    // {
    //   name: 'account',
    //   icon: 'user',
    //   path: '/account',
    //   routes: [
    //     // {
    //     //   path: '/account',
    //     //   redirect: '/account/center',
    //     // },
    //     {
    //       name: 'center',
    //       icon: 'smile',
    //       path: '/account/center',
    //       component: './account/center',
    //     },
    //     {
    //       name: 'settings',
    //       icon: 'smile',
    //       path: '/account/settings',
    //       component: './account/settings',
    //     },
    //   ],
    // },
    {
      path: '/',
      redirect: '/dashboard/analysis',
    },
    {
      component: '404',
    },
  ],
  access: {},
  // Theme for antd: https://ant.design/docs/react/customize-theme-cn
  theme: {
    // 如果不想要 configProvide 动态设置主题需要把这个设置为 default
    // 只有设置为 variable， 才能使用 configProvide 动态设置主色调
    // https://ant.design/docs/react/customize-theme-variable-cn
    'root-entry-name': 'variable',
  },
  // esbuild is father build tools
  // https://umijs.org/plugins/plugin-esbuild
  esbuild: {},
  title: false,
  ignoreMomentLocale: true,
  proxy: proxy[REACT_APP_ENV || 'dev'],
  manifest: {
    basePath: '/',
  },
  // Fast Refresh 热更新
  fastRefresh: {},
  openAPI: [
    {
      requestLibPath: "import { request } from 'umi'",
      // 或者使用在线的版本
      // schemaPath: "https://gw.alipayobjects.com/os/antfincdn/M%24jrzTTYJN/oneapi.json"
      schemaPath: join(__dirname, 'oneapi.json'),
      mock: false,
    },
    {
      requestLibPath: "import { request } from 'umi'",
      schemaPath: 'https://gw.alipayobjects.com/os/antfincdn/CA1dOm%2631B/openapi.json',
      projectName: 'swagger',
    },
  ],
  nodeModulesTransform: {
    type: 'none',
  },
  mfsu: {},
  webpack5: {},
  exportStatic: {},
});
