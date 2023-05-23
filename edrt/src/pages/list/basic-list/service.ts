import { request } from 'umi';
import type { BasicListItemDataType } from './data.d';

type ParamsType = {
  count?: number;
} & Partial<BasicListItemDataType>;


export async function queryFakeList(
  params: ParamsType,
): Promise<{ data: { list: BasicListItemDataType[] } }> {
  const result = await request('http://127.0.0.1:8080/project/main', {
    params,
  });
  return {
    data: {
      list: result,
    },
  };
}

export async function removeFakeList(
  params: ParamsType,
): Promise<{ data: { list: BasicListItemDataType[] } }> {
  const name = params.name
  await request(`http://127.0.0.1:8080/project/delete/${name}`, {
    method: 'DELETE',
  });
  const result = await request('http://127.0.0.1:8080/project/main', {
    count: 50,
  });
  return {
    data: {
      list: result,
    },
  };
}

export async function addFakeList(
  params: ParamsType,
): Promise<{ data: { list: BasicListItemDataType[] } }> {

  await request('http://127.0.0.1:8080/project/add', {
    method: 'POST',
    data: {
      ...params,
    },
  });

  const result = await request('http://127.0.0.1:8080/project/main', {
    count: 50,
  });
  return {
    data: {
      list: result,
    },
  };
}

export async function updateFakeList(
  params: ParamsType,
): Promise<{ data: { list: BasicListItemDataType[] } }> {
  await request('http://127.0.0.1:8080/project/update', {
    method: 'POST',
    data: {
      ...params,
    },
  });
  const result = await request('http://127.0.0.1:8080/project/main', {
    count: 50,
  });
  // result.forEach((item: BasicListItemDataType, i: number) => {
  //   result[i] = { ...item, tag: 'flag' };
  // });
  console.log(result)
  return {
    data: {
      list: result,
    },
  };
}
