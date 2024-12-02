import com.beust.ah.A;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.net.ResponseCache;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TR_01 {
    @Test
    public void TrendYolForgotPasswordWebServiceSecurityCheck(){
        /*Şifremi yenileme eposta hizmeti için atılan client-site tarafındaki api alindi ve isteği 403 döndürdüğünün ve
        bilgilendirme mesajında "This website is using a security service to protect itself from online attacks. The action you
        just performed triggered the security solution. There are several actions that could trigger this block including
        submitting a certain word or phrase, a SQL command or malformed data." ve "You are unable to access" bu
         mesajların döndüğünün kontrolünü sağlayın.

        */
/*
curl 'https://auth.trendyol.com/forgotpassword' \
  -H 'accept: *' \
                -H 'accept-language: tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7' \
        -H 'application-id: 1' \
        -H 'content-type: application/json;charset=UTF-8' \
        -H 'cookie: hvtb=1; platform=web; _tt_enable_cookie=1; _ttp=2_IBllTCUFlCJubEeTWZcMjBoMx; _ym_uid=1709400718635269560; _ym_d=1731536964; OptanonAlertBoxClosed=2024-11-13T22:29:35.710Z; pid=68a59571-9d9c-4b66-b9d9-eaf114e38b01; WebAbTesting=A_80-B_28-C_3-D_77-E_6-F_51-G_50-H_44-I_58-J_65-K_3-L_88-M_9-N_13-O_14-P_87-Q_24-R_38-S_87-T_37-U_62-V_70-W_54-X_35-Y_44-Z_80; _gcl_au=1.1.1384282133.1731536976; _fbp=fb.1.1731536976740.456517295581118460; _gid=GA1.2.236343973.1732479114; COOKIE_TY.Anonym=tx=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cm46dHJlbmR5b2w6YW5vbmlkIjoiNzAxOTUwNzRhYWEwMTFlZmEyOTk2ZWY1MGM2NWNkZGMiLCJyb2xlIjoiYW5vbiIsImF0d3J0bWsiOiI3MDE5NTA3Mi1hYWEwLTExZWYtYTI5OS02ZWY1MGM2NWNkZGMiLCJhcHBOYW1lIjoidHkiLCJhdWQiOiJzYkF5ell0WCtqaGVMNGlmVld5NXR5TU9MUEpXQnJrYSIsImV4cCI6MTg5MDI2NzE1MiwiaXNzIjoiYXV0aC50cmVuZHlvbC5jb20iLCJuYmYiOjE3MzI0NzkxNTJ9.xI1D611x8T1ZPuS9gTKziZEfetl3wg2jEIpncZg5wAA; _hjSessionUser_3408421=eyJpZCI6IjliNmQwYjYwLTE5YmMtNWMzZC1hNWIwLTVkNWUyY2VhZTMxYyIsImNyZWF0ZWQiOjE3MzI0ODUyNjQyNzMsImV4aXN0aW5nIjp0cnVlfQ==; _ga=GA1.1.923092528.1709400705; WebAbDecider=ABBMSA_B-ABDsNlp_2-ABAdvertSlotPeriod_2-ABLBB_B-ABRRIn_B-ABSCB_B-ABSuggestionHighlight_B-ABBP_B-ABCatTR_B-ABCA_B-ABSuggestionTermActive_B-ABAZSmartlisting_62-ABBH2_B-ABMB_B-ABFRA_2-ABMRF_1-ABARR_B-ABMA_B-ABSP_B-ABPastSearches_B-ABSuggestionJFYProducts_B-ABSuggestionQF_B-ABBSA_D-ABBadgeBoost_A-ABRelevancy_1-ABFilterRelevancy_1-ABSmartlisting_65-ABSuggestionBadges_B-ABProductGroupTopPerformer_B-ABOpenFilterToggle_2-ABRR_2-ABBS_2-ABSuggestionPopularCTR_B; VisitCount=19; SearchMode=1; sid=8mC31di8Rz; ForceUpdateSearchAbDecider=forced; WebRecoAbDecider=ABbasketRecoVersion_1-ABshopTheLookVersion_1-ABcrossRecoAdsVersion_1-ABsimilarRecoAdsVersion_1-ABsimilarSameBrandVersion_1-ABcompleteTheLookVersion_1-ABattributeRecoVersion_1-ABcollectionRecoVersion_1-ABcrossRecoVersion_1-ABsimilarRecoVersion_1-ABcrossSameBrandVersion_1-ABpdpGatewayVersion_1-ABnavigationSideMenuVersion_sideMenu%3AinitialTest_1%28M%29-ABnavigationSectionVersion_section%3AazSectionTest_2%28M%29-ABhomepageVersion_performanceSorting%3AwidgetPerformanceFreqV1_3.sorter%3AhomepageSorterNewTest_e.firstComponent%3AinitialNewTest_1%28M%29; msearchAb=ABSearchFETestV1_A-ABSuggestionPS_A; recoAb=ABRecoBETestV1_B-ABRecoFirstTest_A; AbTesting=RecoSecond_B-SFWDBSR_A-SFWDRS_A-SFWDSAOF_A-SFWDSFAG_B-SFWDTKV2_A-STSBuynow_B-WCBsSvShowTimer_B-ZZTEST1_A%7C1732647463; FirstSession=0; __cf_bm=uCcgrtpx3EbVww0Ldfwgnv2ckKDc3BcUbk9JIY280lU-1732645663-1.0.1.1-3RUpx9CBw1fB9TipQ7BckRPiaewD9mZojWVw6FduWPAJFd0oMN1aOZxLRbFSRKgkkRnnhpxtDz0vFUNRVmWEiA; _cfuvid=R3oxq3Yp09ZHr0Bsvev4oHeq7Frspn.kKVM_7cgc._U-1732645663259-0.0.1.1-604800000; _ym_isad=2; __cflb=04dToT2xh9suvQe8SsHWjCWY7HUwFMk4GGqDSqrfZo; _ga_1=GS1.1.1732645669.12.1.1732645715.0.0.1494051790; OptanonConsent=isGpcEnabled=0&datestamp=Tue+Nov+26+2024+21%3A28%3A36+GMT%2B0300+(GMT%2B03%3A00)&version=6.30.0&isIABGlobal=false&hosts=&genVendors=V77%3A0%2CV67%3A0%2CV79%3A0%2CV71%3A0%2CV69%3A0%2CV7%3A0%2CV5%3A0%2CV9%3A0%2CV1%3A0%2CV70%3A0%2CV3%3A0%2CV68%3A0%2CV78%3A0%2CV17%3A0%2CV76%3A0%2CV80%3A0%2CV16%3A0%2CV72%3A0%2CV10%3A0%2CV40%3A0%2C&consentId=e1933112-6458-42d6-b2a0-0b78c46c81b2&interactionCount=4&landingPath=NotLandingPage&groups=C0002%3A1%2CC0004%3A1%2CC0003%3A1%2CC0001%3A1%2CC0007%3A1%2CC0009%3A1&AwaitingReconsent=false&geolocation=TR%3B06' \
        -H 'culture: tr-TR' \
        -H 'origin: https://auth.trendyol.com' \
        -H 'priority: u=1, i' \
        -H 'referer: https://auth.trendyol.com/static/fragment?application-id=1&storefront-id=1&culture=tr-TR&language=tr&debug=False' \
        -H 'sec-ch-ua: "Google Chrome";v="131", "Chromium";v="131", "Not_A Brand";v="24"' \
        -H 'sec-ch-ua-mobile: ?0' \
        -H 'sec-ch-ua-platform: "Windows"' \
        -H 'sec-fetch-dest: empty' \
        -H 'sec-fetch-mode: cors' \
        -H 'sec-fetch-site: same-origin' \
        -H 'storefront-id: 1' \
        -H 'user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36' \
        --data-raw '{"email":"eneskmnc1818@gmail.com"}'

 */
        String url="https://auth.trendyol.com/forgotpassword";



        JSONObject requestbody=new JSONObject();
        requestbody.put("email","eneskmnc1818@gmail.com");

       // System.out.println(requestbody);

        //burada bütün header ların zorunlu olduğu varsayıldı
        Response response= given().contentType(ContentType.JSON).when().body(requestbody.toString())
                .header("application id",1)
                .header("content-type","application/json;charset=UTF-8")
                .header("culture","tr-TR")
                .header("origin","https://auth.trendyol.com")
                .header("priority","u=1, i")
                .header("referer","https://auth.trendyol.com/static/fragment?application-id=1&storefront-id=1&culture=tr-TR&language=tr&debug=False")
                .header("sec-ch-ua","\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"")
                .header("sec-ch-ua-mobile","?0")
                .header("sec-ch-ua-platform","Windows")
                .header("sec-fetch-dest","empty")
                .header("sec-fetch-mode","cors")
                .header("sec-fetch-site","same-origin")
                .header("storefront-id","1")
                .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36")
                .post(url);
     Cookies cookies=response.getDetailedCookies();
        System.out.println(cookies);
        Response response2= given().contentType(ContentType.JSON).when().body(requestbody.toString())
                .cookies(cookies)
                .header("application id",1)
                .header("content-type","application/json;charset=UTF-8")
                .header("culture","tr-TR")
                .header("origin","https://auth.trendyol.com")
                .header("priority","u=1, i")
                .header("referer","https://auth.trendyol.com/static/fragment?application-id=1&storefront-id=1&culture=tr-TR&language=tr&debug=False")
                .header("sec-ch-ua","\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"")
                .header("sec-ch-ua-mobile","?0")
                .header("sec-ch-ua-platform","Windows")
                .header("sec-fetch-dest","empty")
                .header("sec-fetch-mode","cors")
                .header("sec-fetch-site","same-origin")
                .header("storefront-id","1")
                .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36")
                .post(url);

        SoftAssert softassert=new SoftAssert();
        //response.prettyPrint();
        System.out.println(response.getStatusCode());
        response.then().assertThat().statusCode(403);
        String errormessage="This website is using a security service to protect itself from online attacks. The action you just performed triggered the security solution. There are several actions that could trigger this block including submitting a certain word or phrase, a SQL command or malformed data.";
        String errormessage2="You are unable to access";
        String responseBody=response2.getBody().asString();
        softassert.assertTrue(responseBody.contains(errormessage),"hata mesajı ekrana gelmedi");
        softassert.assertTrue(responseBody.contains(errormessage2),"hata mesaji ekrana gelmedi");
        softassert.assertAll();



    }
}
