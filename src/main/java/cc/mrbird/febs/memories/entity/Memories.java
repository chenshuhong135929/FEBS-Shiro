package cc.mrbird.febs.memories.entity;

import java.util.Date;

import cc.mrbird.febs.common.utils.validator.UpdateGroup;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotEmpty;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2020-05-15 10:42:48
 */
@Data
@TableName("memories")
public class Memories {

    /**
     *
     */
    @TableId(value = "id")
    private String id;

    /**
     *
     */
    @TableField("memories_name")
    private String memoriesName;

    /**
     *
     */
    @TableField("memories_url")
    @NotEmpty(message="图片必选",groups = {UpdateGroup.class})
    private String memoriesUrl;

    /**
     *
     */
    @TableField("memories_date")
    private Date memoriesDate;

    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;
}
