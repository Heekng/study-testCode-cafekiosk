package sample.cafekiosk.spring.api.service.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

@ExtendWith(MockitoExtension.class) // 모키토 이용해서 목 만들거야!
class MailServiceTest {

    @Mock
    //    @Spy
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        //        MailSendClient mailSendClient = mock(MailSendClient.class);
        //        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);

        //        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        // stubbing
        // spy를 쓰면 when을 사용할 수 없다.(실제 객체를 사용하기 때문에)
        //        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString())).thenReturn(true);
        //        doReturn(true).when(mailSendClient)
        //            .sendEmail(anyString(), anyString(), anyString(), anyString());

        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
            .willReturn(true);

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }

}
