import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class mainClass {

    public void getResponseData(char[] xmlData) throws Exception {
        String xmlResponseData = new String(xmlData);

        System.out.println("response" + xmlResponseData);
        // xmlResponseData-ийн утгыг Json хэлбэртэй өгөгдөл болгож төрөл хувиргалт хийх
        JSONObject data = XML.toJSONObject(xmlResponseData);
        JSONObject a = data.getJSONObject("a");

        if(a.has("Order")){
            JSONObject order = (JSONObject) a.get("Order");
            int visit = (int) order.get("visit");
            String guid = (String)  order.get("guid");
            String kdsstate = (String)  order.get("kdsstate");
            JSONObject externalProps = order.getJSONObject("ExternalProps");

            Integer qmsNumber = 0;
            String name = "";
            Boolean voiceState = false;

            JSONArray arrProps = externalProps.getJSONArray("Prop");
            for(int i = 0; i < arrProps.length(); i++){
                JSONObject prop = arrProps.getJSONObject(i);
                name =(String) prop.get("name");
                if(name.equals("{7DC7AF79-1D00-4573-BE8A-A02C6FA3B430}")){
                    qmsNumber = (Integer) prop.get("value");
                }
            }
            System.out.println("visit ==> : " + visit);
            System.out.println("guid ==> : " + guid);
            System.out.println("kdsstate ==> : " + kdsstate);
            System.out.println("qmsNumber ==> : " + qmsNumber);
            InsertApp app = new InsertApp();
            app.insert(visit, qmsNumber, kdsstate, voiceState, guid);
            Voice voice = new Voice();
            voice.voiceP();
        }
    }
}

