interface TypeAPI {
  get: <T>(url: string) => Promise<T>;
}

const API: TypeAPI = {
  async get<T>(url: string): Promise<T> {
    const res = await fetch(url);
    return await res.json();
  },
};

export default API;
