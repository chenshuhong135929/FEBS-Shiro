package cc.mrbird.febs.memories.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.memories.entity.Memories;
import cc.mrbird.febs.memories.mapper.MemoriesMapper;
import cc.mrbird.febs.memories.service.IMemoriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author MrBird
 * @date 2020-05-15 10:42:48
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MemoriesServiceImpl extends ServiceImpl<MemoriesMapper, Memories> implements IMemoriesService {

    private final MemoriesMapper memoriesMapper;

    @Override
    public IPage<Memories> findMemoriess(QueryRequest request, Memories memories) {
        LambdaQueryWrapper<Memories> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Memories> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Memories> findMemoriess(Memories memories) {
	    LambdaQueryWrapper<Memories> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMemories(Memories memories) {
        this.save(memories);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMemories(Memories memories) {
        this.saveOrUpdate(memories);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMemories(Memories memories) {
        LambdaQueryWrapper<Memories> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
