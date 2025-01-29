package com.swaraj.swarajhotel.service.interfac;

import com.swaraj.swarajhotel.dto.LoginRequest;
import com.swaraj.swarajhotel.dto.Response;
import com.swaraj.swarajhotel.entity.User;

public interface IUserService {

    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String userId);
}
