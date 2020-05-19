package cc.mrbird.febs.memories.service;

import cc.mrbird.febs.memories.entity.Memories;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2020-05-15 10:42:48
 */
public interface IMemoriesService extends IService<Memories> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param memories memories
     * @return IPage<Memories>
     */
    IPage<Memories> findMemoriess(QueryRequest request, Memories memories);

    /**
     * 查询（所有）
     *
     * @param memories memories
     * @return List<Memories>
     */
    List<Memories> findMemoriess(Memories memories);

    /**
     * 新增
     *
     * @param memories memories
     */
    void createMemories(Memories memories);

    /**
     * 修改
     *
     * @param memories memories
     */
    void updateMemories(Memories memories);

    /**
     * 删除
     * @param ids
     */
    void deleteMemories(String[]  ids );

    /**
     * 获取详情
     * @param id
     * @return
     */
    Memories  findId(String id);
}
