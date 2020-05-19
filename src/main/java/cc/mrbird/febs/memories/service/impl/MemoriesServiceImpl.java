package cc.mrbird.febs.memories.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.memories.entity.Memories;
import cc.mrbird.febs.memories.mapper.MemoriesMapper;
import cc.mrbird.febs.memories.service.IMemoriesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.File;
import java.util.Arrays;
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

    @Value("${web.upload-path}")
    private String uploadPath;
    @Override
    public IPage<Memories> findMemoriess(QueryRequest request, Memories memories) {
        QueryWrapper<Memories> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(memories.getMemoriesName())){
            queryWrapper.like("memories_name",memories.getMemoriesName());
        }
        if (StringUtils.isNotBlank(memories.getCreateTimeFrom())) {
            memories.setCreateTimeFrom(memories.getCreateTimeFrom() + " 00:00:00");
            memories.setCreateTimeTo(memories.getCreateTimeTo() + " 23:59:59");
            queryWrapper.ge("memories_date",memories.getCreateTimeFrom());
            queryWrapper.le("memories_date",memories.getCreateTimeTo());

        }


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
    public void deleteMemories(String []  ids) {
        List<String> list = Arrays.asList(ids);
        for(String id :list){
            Memories memories = this.findId(id);
            //对原有的图片进行删除
            String path = uploadPath.replace("mes/", "");
            File files = new File(path+memories.getMemoriesUrl());
            files.delete();
        }
        this.removeByIds(list);
	}

    @Override
    public Memories findId(String id) {
        return memoriesMapper.findId(id);
    }
}
