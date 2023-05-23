import { PlusOutlined } from '@ant-design/icons';
import { Button, Card, List, Typography, Input } from 'antd';  // 添加Input组件导入语句
import { PageContainer } from '@ant-design/pro-layout';
import { useRequest } from 'umi';
import { queryFakeList } from './service';
import type { CardListItemDataType } from './data.d';
import styles from './style.less';

const { Paragraph } = Typography;



const CardList = () => {
  const { data, loading } = useRequest(() => {
    return queryFakeList({
      count: 8,
    });
  });

  const list = data?.list || [];

  const content = (
    <div className={styles.pageHeaderContent}>
      <p>
        重构推荐新体验，模型选择更省心。让重构变得更简单，选择你想使用的不平衡多分类机器学习模型，快速提升演进异味重构的效率。
      </p>
      <div className={styles.contentLink}>
        <a>
          <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/MjEImQtenlyueSmVEfUD.svg" />{' '}
          快速开始
        </a>
        {/*<a>*/}
        {/*  <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/NbuDUAuBlIApFuDvWiND.svg" />{' '}*/}
        {/*  查看模型指标*/}
        {/*</a>*/}
        <a>
          <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/ohOEPSYdDTNnyMbGuyLb.svg" />{' '}
          查看模型指标
        </a>
      </div>

    </div>

  );

  const extraContent = (
    <div className={styles.extraImg}>
      <img
        alt="这是一个标题"
        src="https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png"
      />
    </div>
  );
  const nullData: Partial<CardListItemDataType> = {};
  return (
    <PageContainer content={content} extraContent={extraContent}>

      {/*<div className={styles.zx}>*/}
      {/*  请选择项目：*/}

      {/*  <select>*/}
      {/*    <option value="litemall">litemall</option>*/}
      {/*  </select>*/}
      {/*</div>*/}


      <div className={styles.zx}>
        <div className={styles.select}>
          <label htmlFor="project-select">选择项目:   </label>
          <select id="project-select" className={styles.select}>
            <option value="litemall">litemall</option>
          </select>
        </div>

      </div>

      <div className={styles.zxx}>


      </div>


      <div className={styles.cardList}>
        <List<Partial<CardListItemDataType>>
          rowKey="id"
          loading={loading}
          grid={{
            gutter: 16,
            xs: 1,
            sm: 2,
            md: 3,
            lg: 3,
            xl: 4,
            xxl: 4,
          }}
          dataSource={[nullData, ...list]}
          renderItem={(item) => {
            if (item && item.title) {
              return (
                <List.Item key={item.id}>
                  <Card
                    hoverable
                    className={styles.card}
                    actions={[<a key="option1">执行</a>, <a key="option2">查询状态</a>, <a key="option3">查看结果</a>]}
                  >
                    <Card.Meta
                      avatar={<img alt="" className={styles.cardAvatar} src={item.avatar} />}
                      title={<a>{item.title}</a>}
                      description={
                        <Paragraph className={styles.item} ellipsis={{ rows: 3 }}>
                          {item.description}
                        </Paragraph>
                      }
                    />
                  </Card>
                </List.Item>
              );
            }

            return (
              <List.Item>
                <Button type="dashed" className={styles.newButton}>
                  <PlusOutlined /> 新增模型
                </Button>
              </List.Item>
            );
          }}
        />
      </div>
    </PageContainer>
  );
};

export default CardList;
