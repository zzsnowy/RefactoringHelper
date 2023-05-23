import { useIntl } from 'umi';
import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-layout';

const Footer: React.FC = () => {
  const intl = useIntl();
  // const defaultMessage = intl.formatMessage({
  //   id: 'app.copyright.produced',
  //   defaultMessage: '蚂蚁集团体验技术部出品',
  // });

  const currentYear = new Date().getFullYear();

  const defaultMessage = 'zzsnowy'
  return (
    <DefaultFooter
       copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'RefactorHelper',
          title: 'RefactorHelper',
          href: 'https://pro.ant.design',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/ant-design/ant-design-pro',
          blankTarget: true,
        },
        {
          key: '让重构变得更简单',
          title: '让重构变得更简单',
          href: 'https://ant.design',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
