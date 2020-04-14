<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use Illuminate\Support\Str;
use Illuminate\Support\Facades\Hash;



class UserController extends Controller
{
    public function register(Request $request){
       
        $user =  User::create([
            'name' => $request['name'],
            'email' => $request['email'],
            'password' => $request['password']
            
        ]);

        return response()->json(['Usuario' => $user], 201);
    }

    public function generateToken(Request $request){
  
        $id = $request->id;
        $user = User::find($id);
        $token = Str::random(60);

        $user->forceFill(
            'api_token'  )->save();

        return ['token' => $token];
    }

    public function login(Request $request)
{
   // $this->validateLogin($request);

    if ($this->attemptLogin($request)) {
        $user = $this->guard()->user();
        $user->generateToken();

        return response()->json([
            'data' => $user->toArray(),
        ]);
    }

    return $this->sendFailedLoginResponse($request);
}

    public function users(){

        $users = User::all();

        return response()->json($users, 200);

    }
}
