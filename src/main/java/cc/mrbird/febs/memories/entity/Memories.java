package cc.mrbird.febs.memories.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
    private String memoriesUrl;

    /**
     *
     */
    @TableField("memories_date")
    private Date memoriesDate;

}
