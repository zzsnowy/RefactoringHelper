import { request } from 'umi';
import type { CardListItemDataType } from './data.d';

export async function queryFakeList(params: {
  count: number;
}): Promise<{ data: { list: CardListItemDataType[] } }> {
  const result = await request('http://127.0.0.1:8083/model/main', {
    params,
  });
  return {
    data: {
      list: result,
    },
  };
}
