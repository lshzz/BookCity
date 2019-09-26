package com.asterism.fresk.dao;

import android.content.Context;
import android.util.Log;

import com.asterism.fresk.dao.bean.BookBean;
import com.asterism.fresk.dao.core.DatabaseHelper;
import com.asterism.fresk.util.DateUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

/**
 * 书籍表访问器类
 *
 * @author Ashinch
 * @email Glaxyinfinite@outlook.com
 * @date on 2019-07-02 14:53
 */
public class BookDao {
    private Context context;

    // ORMLite提供的DAO类对象
    // 第一个泛型是要操作的数据表映射成的实体类
    // 第二个泛型是这个实体类中ID的数据类型
    private Dao<BookBean, Integer> dao;

    /**
     * 构造方法
     *
     * @param context 上下文对象
     */
    public BookDao(Context context) {
        this.context = context;
        try {
            // 从数据库访问基类的单例对象获取Dao
            this.dao = DatabaseHelper.getInstance(context).getDao(BookBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增
     *
     * @param data 欲增加的记录实体类
     */
    public void insert(BookBean data) {
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删
     *
     * @param data 欲删除的记录实体类
     */
    public void delete(BookBean data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 改
     *
     * @param data 欲更改的记录实体类
     */
    public void update(BookBean data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有记录
     *
     * @return 返回所有记录实体类集合
     */
    public List<BookBean> selectAll() {
        List<BookBean> beanList = null;
        try {
            beanList = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    /**
     * 根据上次阅读日期降序排序并通过索引查询记录
     *
     * @param index 索引
     *
     * @return 返回查询到的BookBean
     */
    public BookBean selectByIndexSortReadDate(int index) {
        List<BookBean> beanList = selectAll();
        if (beanList == null || index < 0 || index >= beanList.size()) {
            return null;
        }
        BookBean temp;
        for (int i = 0; i < beanList.size(); i++) {
            for (int j = 0; j < beanList.size() - 1 - i; j++) {
                int res = DateUtils.compareDateByString(beanList.get(j).getReadDate()
                        , beanList.get(j + 1).getReadDate());
                if (res < 0) {
                    temp = beanList.get(j);
                    beanList.set(j, beanList.get(j + 1));
                    beanList.set(j + 1, temp);
                }
            }
        }
        BookBean bean = beanList.get(index);
        beanList.clear();
        return bean;
    }

    /**
     * 根据上次阅读日期降序排序并查询所有记录
     *
     * @return 返回排序后的实体类集合
     */
    public List<BookBean> selectALLSortReadDate() {
        List<BookBean> beanList = selectAll();
        if (beanList == null) {
            return null;
        }
        BookBean temp;
        for (int i = 0; i < beanList.size(); i++) {
            for (int j = 0; j < beanList.size() - 1 - i; j++) {
                int res = DateUtils.compareDateByString(beanList.get(j).getReadDate()
                        , beanList.get(j + 1).getReadDate());
                if (res < 0) {
                    temp = beanList.get(j);
                    beanList.set(j, beanList.get(j + 1));
                    beanList.set(j + 1, temp);
                }
            }
        }
        return beanList;
    }

    /**
     * 根据Path来查询数据库中是否存在
     *
     * @param path 要进行比对的Path
     * @return 返回数据库中是否有此书籍
     */
    public boolean queryIsExistByPath(String path) {

        List<BookBean> beanList = null;
        try {
            beanList =  dao.queryBuilder().where().eq("file_path", path).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //判断是否有值
        if(beanList == null || beanList.size() == 0) {
            return false;
        }
        return true;
    }
}