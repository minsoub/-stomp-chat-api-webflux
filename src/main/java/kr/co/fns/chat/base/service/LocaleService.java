package kr.co.fns.chat.base.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service("LocaleService")
public class LocaleService {



    /**
     * 회원에게 발송과 관련하여 정해진 언어로만 발송을 하도록 강제한다.
     * 현재는 이메일 발송만 해당하는 것이라 함부로 사용X - jyson
     */
    public Locale sendConverLocale(Locale locale) {

        /**
         * 22/04/25, 현재 'ko', 'en'만 기본으로 하며
         * 지원하지 않을경우 'en' default이다.
         */
        if("en".equals(locale.getLanguage()) || "ko".equals(locale.getLanguage())) {

        } else {
            locale = new Locale("en","US");
        }
        return locale;
    }
}
