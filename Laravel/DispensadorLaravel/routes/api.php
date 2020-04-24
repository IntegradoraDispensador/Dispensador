<?php

use Illuminate\Http\Request;


/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

 Route::middleware('auth:api')->get('/user', function (Request $request) {
     return $request->user();
});

Route::post('register', 'UserController@register');

//Route::post('login', 'UserController@login');

Route::post('generate', 'UserController@generateToken');


Route::group(['prefix' => 'auth'], function () {
    Route::post('login', 'AuthController@login');
    Route::post('signup', 'AuthController@signup');
  
    Route::group(['middleware' => 'auth:api'], function() {
        Route::get('logout', 'AuthController@logout');
        Route::get('user', 'AuthController@user');
    });
});

Route::get('users', 'UserController@users');

Route::get('ultrasonico', 'ApiController@getUltrasonico');

//Route::post('tokens', 'ApiController@setAdafruitToken');

//aio_oPUI61fBuw2KPc0CoSSTafNQ2Qss