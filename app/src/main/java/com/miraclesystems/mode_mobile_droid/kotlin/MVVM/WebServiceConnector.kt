package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import android.os.AsyncTask
import android.util.Base64
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.API_PASSWORD
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.API_USER

import java.io.*
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL
import javax.net.ssl.HttpsURLConnection


// define the delegate methods
interface WebServiceConnectorDelegate{
    fun onSuccess(jsonString : String)
    fun onError(errorString : String)
}

class WebServiceConnector(urlString : String, delegate : WebServiceConnectorDelegate) {

    var cookieString = ""

    // url for the restful api
    private var serverURL: URL? = null

    // the delegate to handle events
    private var delegate: WebServiceConnectorDelegate? = null

    // only used for post
    private var postData: String? = null
    var tokenString: String? = null


    init {
        this.serverURL = URL(urlString)
        this.delegate = delegate
    }

    /**
     * post to an api
     */
    fun post(postData: String, tokenString: String) {
        this.postData = postData
        this.tokenString = tokenString
        doPostTask().execute()
    }

    /**
     * post to an api
     */
    fun post(postData: String) {
        this.postData = postData
        doPostTask().execute()
    }

    /**
     * do a get from an api
     */
    fun get() {
        doGetTask().execute()
    }

    fun put(postData: String) {
        this.postData = postData
        doPutTask().execute()
    }

    inner class doPostTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void): String {
            try {
                // Set up request
                println("doPostTask>>>" + serverURL)
                val connection: HttpURLConnection = serverURL?.openConnection() as HttpURLConnection

                // conn.setRequestProperty("ClientSource", "web");
                if (!cookieString.equals("", ignoreCase = true)) {
                    connection.setRequestProperty("Cookie", cookieString)
                }

                connection.setReadTimeout(45000)
                connection.setConnectTimeout(45000)
                // Default is GET so you must override this for post
                connection.requestMethod = "POST"
                // To send a post body, output must be true
                connection.doOutput = true
                // set the request to type json
                if (!tokenString.isNullOrEmpty()) {
                    connection.setRequestProperty("Authorization", "Bearer " + tokenString);
                }
                connection.setRequestProperty("Content-Type", "application/json")
                // Create the stream
                val outputStream: OutputStream = connection.outputStream
                // Create a writer container to pass the output over the stream

                val os: OutputStream = connection.getOutputStream()
                val writer = BufferedWriter(
                    OutputStreamWriter(os, "UTF-8")
                )
                writer.write(postData)
                writer.flush()
                writer.close()

                val responseCode = connection.getResponseCode()

                if (responseCode == HTTP_OK) {

                    // Create an input stream to read the response
                    val inputStream =
                        BufferedReader(InputStreamReader(connection.inputStream)).use {
                            // Container for input stream data
                            val response = StringBuffer()
                            var inputLine = it.readLine()
                            // Add each line to the response container
                            while (inputLine != null) {
                                response.append(inputLine)
                                inputLine = it.readLine()
                            }
                            it.close()
                            // TODO: Add main thread callback to parse response
                            println(">>>> Response: $response")
                            connection.disconnect()
                            return response.toString()
                        }

                } else {

                    return "false : $responseCode"

                }
            } catch (e: Exception) {

                return "exception : ${e.localizedMessage}"
            }
        }

        protected fun onProgressUpdate(vararg progress: Int) {
        }

        override fun onPostExecute(result: String?) {

            if (result?.startsWith("false") == true || result?.startsWith("exception") == true) {
                delegate?.onError(result!!)
            } else {
                delegate?.onSuccess(result!!)
            }
        }

    }


    inner class doPutTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void): String {
            try {
                // Set up request
                println("doPutTask>>>" + serverURL)
                val connection: HttpURLConnection = serverURL?.openConnection() as HttpURLConnection

                // conn.setRequestProperty("ClientSource", "web");
                if (!cookieString.equals("", ignoreCase = true)) {
                    connection.setRequestProperty("Cookie", cookieString)
                }

                connection.setReadTimeout(45000)
                connection.setConnectTimeout(45000)
                // Default is GET so you must override this for post
                connection.requestMethod = "PUT"
                // To send a post body, output must be true
                connection.doOutput = true
                // set the request to type json
                if (!tokenString.isNullOrEmpty()) {
                    connection.setRequestProperty("Authorization", "Bearer " + tokenString);
                }
                connection.setRequestProperty("Content-Type", "application/json")
                // Create the stream
                val outputStream: OutputStream = connection.outputStream
                // Create a writer container to pass the output over the stream

                val os: OutputStream = connection.getOutputStream()
                val writer = BufferedWriter(
                    OutputStreamWriter(os, "UTF-8")
                )
                writer.write(postData)
                writer.flush()
                writer.close()


                val responseCode = connection.getResponseCode()

                if (responseCode == HTTP_OK) {

                    // Create an input stream to read the response
                    val inputStream =
                        BufferedReader(InputStreamReader(connection.inputStream)).use {
                            // Container for input stream data
                            val response = StringBuffer()
                            var inputLine = it.readLine()
                            // Add each line to the response container
                            while (inputLine != null) {
                                response.append(inputLine)
                                inputLine = it.readLine()
                            }
                            it.close()
                            // TODO: Add main thread callback to parse response
                            println(">>>> Response: $response")
                            connection.disconnect()
                            return response.toString()
                        }

                } else {

                    return "false : $responseCode"

                }
            } catch (e: Exception) {

                return "exception : ${e.localizedMessage}"
            }
        }

        protected fun onProgressUpdate(vararg progress: Int) {
        }

        override fun onPostExecute(result: String?) {

            if (result?.startsWith("false") == true || result?.startsWith("exception") == true) {
                delegate?.onError(result!!)
            } else {
                delegate?.onSuccess(result!!)
            }
        }

    }


    inner class doGetTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void): String {
            println("doGetTask>>>" + serverURL)

            try {
                // Set up request
                val connection: HttpsURLConnection =
                    serverURL?.openConnection() as HttpsURLConnection
                if (!tokenString.isNullOrEmpty()) {
                    connection.setRequestProperty("Authorization", "Bearer " + tokenString);
                }

                val username = API_USER
                val password = API_PASSWORD
                val credentials = username + ":" + password
                val AUTH = "Basic " + Base64.encodeToString(credentials.toByteArray(Charsets.UTF_8), Base64.DEFAULT).replace("\n", "")

                //connection.setRequestProperty("Authorization", AUTH);
                connection.setRequestProperty("Authorization", "Basic bWNmcFJFU1Q6IzByYWNsZVIzU1RBUCFzIw==")

                val responseCode = connection.getResponseCode()

                println("responseCode>>>" + responseCode)

                if (responseCode == HTTP_OK) {

                    // Create an input stream to read the response
                    val inputStream =
                        BufferedReader(InputStreamReader(connection.inputStream)).use {
                            // Container for input stream data
                            val response = StringBuffer()
                            var inputLine = it.readLine()
                            // Add each line to the response container
                            while (inputLine != null) {
                                response.append(inputLine)
                                inputLine = it.readLine()
                            }
                            it.close()
                            // TODO: Add main thread callback to parse response
                            println(">>>> Response: $response")
                            connection.disconnect()
                            return response.toString()
                        }

                } else {
                    return "false : $responseCode"

                }
            } catch (e: Exception) {

                return "exception : ${e.localizedMessage}"
            }
        }

        protected fun onProgressUpdate(vararg progress: Int) {
        }

        override fun onPostExecute(result: String?) {
            if (result?.startsWith("false") == true || result?.startsWith("exception") == true) {
                delegate?.onError(result!!)
            } else {
                delegate?.onSuccess(result!!)
            }
        }
    }
}




