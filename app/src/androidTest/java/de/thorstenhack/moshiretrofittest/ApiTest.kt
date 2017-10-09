package de.thorstenhack.moshiretrofittest

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ApiTest {

    @Test
    fun testMoshi() {
        val millisUntilReturn = ApiManagerMoshi.execute(object : Callback<List<GithubRepoResponse>> {
            override fun onResponse(call: Call<List<GithubRepoResponse>>, response: Response<List<GithubRepoResponse>>) {}
            override fun onFailure(call: Call<List<GithubRepoResponse>>, t: Throwable?) {}
        })
        // call should not take longer than 300ms before returning
        assertTrue(millisUntilReturn < 300)
    }

    @Test
    fun testGson() {
        val millisUntilReturn = ApiManagerGson.execute(object : Callback<List<GithubRepoResponse>> {
            override fun onResponse(call: Call<List<GithubRepoResponse>>, response: Response<List<GithubRepoResponse>>) {}
            override fun onFailure(call: Call<List<GithubRepoResponse>>, t: Throwable?) {}
        })
        assertTrue(millisUntilReturn < 300)
    }


}
