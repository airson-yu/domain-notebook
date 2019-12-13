package com.airson.domain.notebook.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: TechGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark group table
 * @time 2019-12-13 17:56:27
 */
@Data
public class Group {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * sender_id concat target_id for query(e.g.: 23_54)
	 */
	private String uniqueId;
	
	/**
	 * sender username
	 */
	private String groupName;
	
	/**
	 * sender avatar
	 */
	private String groupAvatar;
	
	/**
	 * receiver uid
	 */
	private Long type;
	
	/**
	 * content_type: 1.text, 10.sticker, 20.image, 21.audio, 22.video, 23.file, 30.audio_call, 31.video_call, 40.location,40.contact_card, 50.url_link, 100.transfer, 101.red_envelope
	 */
	private Integer level;
	
	/**
	 * message content
	 */
	private String ownerUid;
	
	/**
	 * json str for attached data e.g.: file_url, file_path, sticker_id, uid, link_url
	 */
	private String createUid;
	
	/**
	 * state: 1.normal 2.recall 3.hide 9.abnormal 99.deleted
	 */
	private Integer state;
	
	/**
	 * send_state: 1.not_send, 2.sending, 3.send done, 4.send fail
	 */
	private Integer sendState;
	
	/**
	 * create time
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/**
	 * update time（update automatically）
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}