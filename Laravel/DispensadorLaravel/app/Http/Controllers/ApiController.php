<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use GuzzleHttp\Exception\GuzzleException;
use GuzzleHttp\Client;
use App\User;

class ApiController extends Controller
{
    public function postOn(Request $request){

        $client = new \GuzzleHttp\Client();
        $response = $client->request('POST', 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data', [
            'form_params' => [
                'value' => 'ON',
            ]
        ]);
    }

    public function postOff(Request $request){

        $client = new \GuzzleHttp\Client();
        $response = $client->request('POST', 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data', [
            'form_params' => [
                'value' => 'OFF',
            ]
        ]);
    }

    public function getUltrasonico(Request $request){

        $client = new \GuzzleHttp\Client();
        $response = $client->request('GET', 'https://io.adafruit.com/api/v2/ubaldo01/feeds/ultrasonico/data', [
            'headers' => [
                'X-AIO-Key' => 'aio_TAPe68HndORlKq8E1Iv20EDtNLOS',

               
            ],

            'query' => ['limit' => 50]
           
        ]);

       // $token  = $request->getContent();

        return $response->getBody();

        
    }

    //aio_TAPe68HndORlKq8E1Iv20EDtNLOS

    public function setAdafruitToken()
    {
      //  return $request->user()->setAdafruitToken($request->token);
            $data = json_decode($request->getContent());

            $token = "aaaas";

            return view('views.prueba')->with('token', $token);




    }

    public function getAdafruitToken(Request $request)
    {
        return $request->user()->getAdafruitToken();
    }

}
