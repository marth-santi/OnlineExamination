interface TypeAPI {
  get: <T>(url: string) => Promise<T>;
  postJSON: <dataType, ObjectType>(
    url: string,
    data: dataType
  ) => Promise<ObjectType>;
  postString: <dataType>(url: string, data: dataType) => Promise<string>;
}

const API: TypeAPI = {
  async get<T>(url: string): Promise<T> {
    const res = await fetch(url);
    return await res.json();
  },
  async postJSON<T, K>(url: string, data: T): Promise<K> {
    const res = await fetch(url, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(data),
    });
    return await res.json();
  },
  async postString<T>(url: string, data: T): Promise<string> {
    const res = await fetch(url, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(data),
    });
    return await res.text();
  },
};

export default API;
