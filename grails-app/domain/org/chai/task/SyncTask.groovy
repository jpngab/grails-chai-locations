package org.chai.task

/*
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Clinton Health Access Initiative nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CLINTON HEALTH ACCESS INITIATIVE BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import org.apache.commons.io.IOUtils;

class SyncTask extends Task {
	
	def syncService
	
	def executeTask() {
		File outputFile = new File(getFolder(), getOutputFilename())
		outputFile.createNewFile()

		def fileWriter = new FileWriter(outputFile)		
		try {
			syncService.syncFromFullList()
			IOUtils.write("Location sync was successful.", fileWriter)
		} catch (Exception e) {
			IOUtils.write("An exception occurred trying to sync locations: "+e.message, fileWriter)
		}
		
		fileWriter.flush()
		IOUtils.closeQuietly(fileWriter)
	}
	
	String getInformation() {
		return null
	}
	
	boolean isUnique() {
		return true
	}
	
	def cleanTask() {
		// nothing to do here
	}
	
	String getOutputFilename() {
		return "errors.txt"
	}
	
	String getFormView() {
		return null
	}
	
	Map getFormModel() {
		return null
	}
	
}