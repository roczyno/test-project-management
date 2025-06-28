package com.roczyno.project_management.service;

import com.roczyno.project_management.model.Invitation;

public interface InvitationService {
    String sendInvitation(String email,Long projectId);
    Invitation acceptInvitation(String token,Long userId) ;
    String getTokenByUserMail(String userEmail);
    void deleteToken(String token);
}
