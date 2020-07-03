package com.orange.admin.pojo.admin;
/**
 * 数据库备份记录实体类
 */
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class DatabaseBak {

	/**
	 * 
	 */

	@Column(nullable=false,length=11)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Id
	private Long id;//唯一id

	@Column(nullable=false,length=32)
	private String filename;//备份的文件名
	
	@Column(nullable=false,length=128)
	private String filepath;//备份的文件路径


	@Column(nullable=false)
	@CreatedDate
	private Date createTime;//创建时间

	@Column(nullable=false)
	@LastModifiedDate
	private Date updateTime;//更新时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "DatabaseBak{" +
				"id=" + id +
				", filename='" + filename + '\'' +
				", filepath='" + filepath + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
