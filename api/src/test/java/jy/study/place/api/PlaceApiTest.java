package jy.study.place.api;

import jy.study.place.domain.entity.PlaceSearchKeyword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PlaceApiTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    @DisplayName("장소 조회 api")
    void searchPlaces() throws Exception {
        //given
        String keyword = "테스트";

        //when & then
        mockMvc.perform(get("/places").param("keyword", keyword))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.*.name").isNotEmpty())
                .andExpect(jsonPath("data.*.address").isNotEmpty())
                .andExpect(jsonPath("data.*.roadAddress").isNotEmpty());
    }

    @Transactional
    @Test
    @DisplayName("검색횟수 상위 10개 키워드 리스트 조회 API")
    void getCountTop10Keywords() throws Exception {
        //given
        for (int i = 0; i < 15; i++)
            entityManager.persist(new PlaceSearchKeyword("테스트"+i, i));

        //when & then
        mockMvc.perform(get("/keywords/count-top-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.*.keyword").isNotEmpty())
                .andExpect(jsonPath("data.*.count").isNotEmpty());
    }
}