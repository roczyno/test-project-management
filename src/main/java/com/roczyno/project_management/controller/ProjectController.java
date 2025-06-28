package com.roczyno.project_management.controller;

import com.roczyno.project_management.dto.request.ProjectRequest;
import com.roczyno.project_management.request.InviteRequest;
import com.roczyno.project_management.service.InvitationService;
import com.roczyno.project_management.service.ProjectService;
import com.roczyno.project_management.service.UserService;
import com.roczyno.project_management.util.ResponseHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final InvitationService invitationService;

    @PostMapping("/create")
    public ResponseEntity<Object> addProject(@RequestBody ProjectRequest req, @RequestHeader("Authorization") String jwt) {
        System.out.println("the jwt"+jwt);
        return ResponseHandler.successResponse(projectService.createProject(req,userService.findUserProfileByJwt(jwt)),
                HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object>getProject(@PathVariable Long id){
        return ResponseHandler.successResponse(projectService.getProject(id),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getAllProjects(@RequestHeader("Authorization") String jwt
            ,@RequestParam(required = false) String category,@RequestParam(required = false) String tag){
       return ResponseHandler.successResponse(projectService.getProjectByTeam(userService.findUserProfileByJwt(jwt),
               category,tag),HttpStatus.OK);
    }
    @PutMapping("/{projectId}")
    public ResponseEntity<Object> updateProject(@PathVariable Long projectId,@RequestBody ProjectRequest projectRequest,
                                                 @RequestHeader("Authorization") String jwt) {

        return ResponseHandler.successResponse(projectService.updateProject(projectId,projectRequest,
                userService.findUserProfileByJwt(jwt).getId()),HttpStatus.OK);
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long projectId,@RequestHeader("Authorization") String jwt){
        return ResponseHandler.successResponse(projectService.deleteProject(projectId,
                userService.findUserProfileByJwt(jwt).getId()),HttpStatus.OK);
    }

    @PostMapping("/add/{projectId}")
    public ResponseEntity<Object> addUserToProject(@PathVariable Long projectId,@RequestHeader("Authorization") String jwt){
        return ResponseHandler.successResponse(projectService.addUserToProject(projectId,
                userService.findUserProfileByJwt(jwt).getId()),HttpStatus.OK);
    }
    @PostMapping("/remove/{projectId}")
    public ResponseEntity<Object> removeUserFromProject(@PathVariable Long projectId,@RequestHeader("Authorization") String jwt)
    {
        return ResponseHandler.successResponse(projectService.removeUserFromProject(projectId,
                userService.findUserProfileByJwt(jwt).getId()),HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Object> searchProject(@RequestParam(required = false) String tag,
                                                   @RequestHeader("Authorization") String jwt) {
        return ResponseHandler.successResponse(projectService.searchProject(tag,
                userService.findUserProfileByJwt(jwt)),HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Object> searchChat(@PathVariable Long projectId) {
      return ResponseHandler.successResponse(projectService.getChatByProjectId(projectId),HttpStatus.OK);
    }
    @PostMapping("/invite")
    public ResponseEntity<Object> inviteToProject(@RequestBody InviteRequest req) {
        return ResponseHandler.successResponse(invitationService.sendInvitation(req.getEmail(), req.getProjectId()),
                HttpStatus.OK);
    }
    @PostMapping("/invite/accept")
    public ResponseEntity<Object> acceptInviteToProject(@RequestHeader("Authorization") String jwt,
                                                            @RequestParam String token) {

        return ResponseHandler.successResponse(invitationService.acceptInvitation(token,
                userService.findUserProfileByJwt(jwt).getId()),HttpStatus.OK);
    }


}
