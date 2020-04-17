<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class ApiController extends Controller
{
    public function post(Request $request){

        $client = new \GuzzleHttp\Client();
        $response = $client->request('POST', 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data', [
            'form_params' => [
                'value' => 'ON',
            ]
        ]);
    }

}
