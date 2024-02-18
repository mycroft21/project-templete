package com.example.demo.ui.dib;

import com.example.demo.application.dib.dto.DibCrudDto.Add;
import com.example.demo.application.dib.dto.DibCrudDto.CreateGroup;
import com.example.demo.application.dib.dto.DibCrudDto.Remove;
import com.example.demo.application.dib.service.DibService;
import com.example.demo.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ably/user/{userId}/dibs")
public class DibController {

	private final DibService dibService;

	@GetMapping
	public ApiResponse findDibGroups(
		@PathVariable(name = "userId") Long userId,
		@RequestParam(name = "pageIndex", required = false) Long pageIndex,
		@PageableDefault Pageable pageable) {
		return ApiResponse.success(
			dibService.findDibGroups(userId, pageIndex, pageable.getPageSize()));
	}

	@PostMapping
	public ApiResponse creatDibGroup(
		@PathVariable(name = "userId") Long userId, @RequestBody CreateGroup request) {
		request.setId(userId);
		return ApiResponse.success(dibService.createGroup(request));
	}

	@DeleteMapping("{dibGroupId}")
	public ApiResponse deleteDibGroup(@PathVariable(name = "dibGroupId") Long dibGroupId) {
		dibService.removeDibGroup(dibGroupId);
		return ApiResponse.success();
	}


	@GetMapping("{dibGroupId}/dibs")
	public ApiResponse findDibs(@PathVariable(name = "dibGroupId") Long dibGroupId,
		@RequestParam(name = "pageIndex", required = false) Long pageIndex,
		@PageableDefault Pageable pageable) {
		return ApiResponse.success(
			dibService.findDibs(dibGroupId, pageIndex, pageable.getPageSize()));
	}

	@PostMapping("{dibGroupId}/dibs")
	public ApiResponse addDib(
		@PathVariable(name = "userId") Long userId,
		@PathVariable(name = "dibGroupId") Long dibGroupId,
		@RequestBody Add request) throws Exception {
		request.setId(userId, dibGroupId);
		dibService.addDib(request);
		return ApiResponse.success();
	}

	@DeleteMapping("{dibGroupId}/dibs")
	public ApiResponse removeDib(@PathVariable(name = "dibGroupId") Long dibGroupId,
		@RequestBody Remove request) {
		request.setId(dibGroupId);
		dibService.removeDib(request);
		return ApiResponse.success();
	}
}
