package com.roczyno.project_management.service;

import com.roczyno.project_management.exception.InvitationException;
import com.roczyno.project_management.model.Invitation;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.repository.InvitationRepository;
import com.roczyno.project_management.util.AppConstants;
import com.roczyno.project_management.util.ErrorConstants;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepo;
    private final EmailService emailService;
    private final ProjectService projectService;
    private final UserService userService;
    @Value("${spring.application.frontendUrl}")
    private String frontendUrl;

    @Override
    public String sendInvitation(String email, Long projectId)  {
        String invitationToken= UUID.randomUUID().toString();
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);
        invitationRepo.save(invitation);
       String invitationUrl=frontendUrl+"/accept_invitation?token="+invitationToken;
		try {
			emailService.sendEmailWithToken(email,"Invitation",invitationUrl);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        return AppConstants.INVITE_SENT;

	}

    @Override
    public Invitation acceptInvitation(String token, Long userId) {
        User user=userService.findUserById(userId);
        Invitation invitation = invitationRepo.findByToken(token);
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
		return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        Invitation invitation = invitationRepo.findByEmail(userEmail);
        if(invitation==null) {
            throw new InvitationException(ErrorConstants.INVALID_INVITATION);
        }
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitationRepo.findByToken(token);
        invitationRepo.delete(invitation);
    }
}
