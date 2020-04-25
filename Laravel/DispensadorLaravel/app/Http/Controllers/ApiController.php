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

            'headers' => [
                'X-AIO-Key' => 'aio_MJKy655V2CjnXX6z6tL6xfrEJM7c'],

                'form_params' => [
                    'value' => 'ON'
                ]

               
          
        ]);
    }

    public function postOff(Request $request){

        $client = new \GuzzleHttp\Client();
        $response = $client->request('POST', 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data', [
            'headers' => [
                'X-AIO-Key' => 'aio_MJKy655V2CjnXX6z6tL6xfrEJM7c'],

            'form_params' => [
                'value' => 'OFF'
            ]
        ]);
    }

    public function aguaOn(Request $request){

        $client = new \GuzzleHttp\Client();
        $response = $client->request('POST', 'https://io.adafruit.com/api/v2/GerardoDM/feeds/agua/data', [

            'headers' => [
                'X-AIO-Key' => 'aio_IUvL84xa2W8MWPU5LkBnsmRQRDx9'],

                'form_params' => [
                    'value' => 'ON'
                ]

               
          
        ]);
    }

    public function aguaOff(Request $request){

        $client = new \GuzzleHttp\Client();
        $response = $client->request('POST', 'https://io.adafruit.com/api/v2/GerardoDM/feeds/agua/data', [
            'headers' => [
                'X-AIO-Key' => 'aio_IUvL84xa2W8MWPU5LkBnsmRQRDx9'],

            'form_params' => [
                'value' => 'OFF'
            ]
        ]);
    }

    public function getUltrasonico(Request $request){

        $client = new \GuzzleHttp\Client();
        $response = $client->request('GET', 'https://io.adafruit.com/api/v2/ubaldo01/feeds/ultrasonico/data', [
            'headers' => [
                'X-AIO-Key' => 'aio_MJKy655V2CjnXX6z6tL6xfrEJM7c',

               
            ],

            'query' => ['limit' => 50]
           
        ]);

       // $token  = $request->getContent();

        return $response->getBody();

        
    }

   
  
  
  
  
  
  
  
    // public function setAdafruitToken()
    // {
    //   //  return $request->user()->setAdafruitToken($request->token);
    //         $data = json_decode($request->getContent());

    //         $token = "aaaas";

    //         return view('views.prueba')->with('token', $token);




    // }

    // public function getAdafruitToken(Request $request)
    // {
    //     return $request->user()->getAdafruitToken();
    // }

}
