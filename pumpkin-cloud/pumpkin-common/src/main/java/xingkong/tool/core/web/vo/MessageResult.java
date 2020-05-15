package xingkong.tool.core.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResult {

    /**
     * 发送状态
     */
    private String sendStatus;

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 消息主题
     */
    private String msgTopical;

    public static MessageResult sendOk() {
        MessageResult messageResult = new MessageResult();
        messageResult.setSendStatus("SEND_OK");
        return messageResult;
    }
}
