package com.asterism.fresk.dao;

import android.content.Context;

import com.asterism.fresk.dao.bean.NoteTypeBean;
import com.asterism.fresk.dao.core.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 笔记类型表访问器类
 *
 * @author Ashinch
 * @email Glaxyinfinite@outlook.com
 * @date on 2019-07-02 14:53
 */
public class NoteTypeDao {
    private Context context;

    // ORMLite提供的DAO类对象
    // 第一个泛型是要操作的数据表映射成的实体类
    // 第二个泛型是这个实体类中ID的数据类型
    private Dao<NoteTypeBean, Integer> dao;

    /**
     * 构造方法
     *
     * @param context 上下文对象
     */
    public NoteTypeDao(Context context) {
        this.context = context;
        try {
            // 从数据库访问基类的单例对象获取Dao
            this.dao = DatabaseHelper.getInstance(context).getDao(NoteTypeBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增
     *
     * @param data 欲增加的记录实体类
     */
    public void insert(NoteTypeBean data) {
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
    public void delete(NoteTypeBean data) {
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
    public void update(NoteTypeBean data) {
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
    public List<NoteTypeBean> selectAll() {
        List<NoteTypeBean> beanList = null;
        try {
            beanList = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beanList;
    }
}