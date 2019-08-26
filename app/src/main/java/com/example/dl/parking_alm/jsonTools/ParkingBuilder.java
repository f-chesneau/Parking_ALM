package com.example.dl.parking_alm.jsonTools;

import android.os.AsyncTask;

import com.example.dl.parking_alm.beans.Parking;
import com.example.dl.parking_alm.beans.Position;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * The type Parking builder.
 */
public class ParkingBuilder
{

    /**
     * The Parkings.
     */
    HashMap<String, Parking> parkings= new HashMap <String, Parking>();

    /**
     * Instantiates a new Parking builder.
     */
    public ParkingBuilder() {

        //1st request https://data.angers.fr/api/records/1.0/search/?dataset=pv_equip_parking&rows=20&start=0&facet=id_parking
        //2nd request https://data.angers.fr/api/records/1.0/search/?dataset=parking-angers&facet=nom&facet=disponible

        new ParkRequest().execute("https://data.angers.fr/api/records/1.0/search/?dataset=pv_equip_parking&rows=20&start=0&facet=id_parking");
        new SpaceRequest().execute("https://data.angers.fr/api/records/1.0/search/?dataset=parking-angers&facet=nom&facet=disponible");
    }

    /**
     * Gets parkings.
     *
     * @return the parkings
     */
    public HashMap<String, Parking> getParkings() {
        return parkings;
    }

    /**
     * Sets parkings.
     *
     * @param parkings the parkings
     */
    public void setParkings(HashMap<String, Parking> parkings) {
        this.parkings = parkings;
    }

    private class SpaceRequest extends AsyncTask <String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String url= strings [0];
            HttpURLConnection con = null ;
            InputStream is = null;
            StringBuffer buffer = new StringBuffer();

            try {
                con = (HttpURLConnection) ( new URL(url)).openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.setDoOutput(false);
                con.connect();

                // Let's read the response

                is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while (  (line = br.readLine()) != null )
                    buffer.append(line + "\r\n");

                is.close();
                con.disconnect();
                return buffer.toString();
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
            finally {
                try { is.close(); } catch(Throwable t) {}
                try { con.disconnect(); } catch(Throwable t) {}
            }
            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            String id="";
            int emptyPlaces=0;

            super.onPostExecute(s);
            try {
                JSONObject result = new JSONObject(s);
                int size= result.getInt("nhits");

                for (int i=0; i<size-1;i++)
                {
                    id= String.valueOf(result.getJSONArray("records").getJSONObject(i).getJSONObject("fields").get("nom"));
                    emptyPlaces= result.getJSONArray("records").getJSONObject(i).getJSONObject("fields").getInt("disponible");

                    parkings.get(id).setEmptyPlaces(emptyPlaces);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class ParkRequest extends AsyncTask <String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String url= strings [0];
            HttpURLConnection con = null ;
            InputStream is = null;
            StringBuffer buffer = new StringBuffer();

            try {
                con = (HttpURLConnection) ( new URL(url)).openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.setDoOutput(false);
                con.connect();

                // Let's read the response

                is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while (  (line = br.readLine()) != null )
                    buffer.append(line + "\r\n");

                is.close();
                con.disconnect();
                return buffer.toString();
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
            finally {
                try { is.close(); } catch(Throwable t) {}
                try { con.disconnect(); } catch(Throwable t) {}
            }
            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            double lat=0.;
            double lon=0.;
            String id="";
            int totalPlaces=0;

            super.onPostExecute(s);
            try {
                JSONObject result = new JSONObject(s);
                int size= result.getInt("nhits");

                for (int i=0; i<size-1;i++)
                {
                    lat= result.getJSONArray("records").getJSONObject(i).getJSONObject("fields").getJSONObject("geo_shape").getJSONArray("coordinates").getDouble(0);
                    lon= result.getJSONArray("records").getJSONObject(i).getJSONObject("fields").getJSONObject("geo_shape").getJSONArray("coordinates").getDouble(1);
                    id= String.valueOf(result.getJSONArray("records").getJSONObject(i).getJSONObject("fields").get("id_parking"));
                    totalPlaces= result.getJSONArray("records").getJSONObject(i).getJSONObject("fields").getInt("nb_places");

                    parkings.put(id, new Parking(id, new Position(lat, lon), totalPlaces, 0));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
