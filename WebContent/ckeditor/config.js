/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	config.filebrowserBrowseUrl = '/WebsiteProject/ckfinder/ckfinder.html';
    config.filebrowserImageBrowseUrl = '/WebsiteProject/ckfinder/ckfinder.html?type=Images';
    config.filebrowserFlashBrowseUrl = '/WebsiteProject/ckfinder/ckfinder.html?type=Flash';
    config.filebrowserUploadUrl = '/WebsiteProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
    config.filebrowserImageUploadUrl = '/WebsiteProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    config.filebrowserFlashUploadUrl = '/WebsiteProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	
};
