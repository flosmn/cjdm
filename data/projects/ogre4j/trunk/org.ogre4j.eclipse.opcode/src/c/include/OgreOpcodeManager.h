#ifndef __OPCODE_MANAGER_H__
#define __OPCODE_MANAGER_H__

#include <Ogre.h>
#include <Opcode.h>
#include <hash_map>
#include <iterator>

namespace Ogre
{
    class OpcodeManager : public Ogre::Singleton<OpcodeManager> 
    {
    private:
        typedef std::map<Ogre::Entity*, Opcode::Model * > OpcodeModelList;
        OpcodeModelList* mOpcodeModels;
        Opcode::CollisionFaces* mFaceCache; 
        Opcode::RayCollider* mRayCollider;
     
    public:
        static OpcodeManager& getSingleton( void )
        {	assert( ms_Singleton );  return ( *ms_Singleton ); }

        static OpcodeManager* getSingletonPtr( void )
        { return ms_Singleton; }

        OpcodeManager();

        virtual ~OpcodeManager();      
        
        void addEntity(Ogre::Entity* entity);
        
        void removeEntity(Ogre::Entity* entity);
        
        /**  Returns the closest entity that will be hit by the ray that defines the rayQuery.	
        	@param rayQuery rayQuery to execute.
        	@return The hit entity.
        */
        Ogre::Entity * getClosestEntity(Ogre::RaySceneQuery * rayQuery);
        
        /** @todo getClosestHit documentation	
        	@param rayQuery
        	@param hitCoord
        	@param normal
        	@return
        */
        bool getClosestHit(Ogre::RaySceneQuery * rayQuery,
                        Ogre::Vector3 &hitCoord, Ogre::Vector3 & normal);

        Ogre::Entity* OpcodeManager::executeQuery(Ogre::RaySceneQuery * rayQuery,  Ogre::Vector3 &hitCoord, Ogre::Vector3 & normal);

        private:
            OpcodeManager(const OpcodeManager& o); 
            const OpcodeManager& operator=(const OpcodeManager& o);

            /** Calculates if the ray collides with the surface of the entity.	
            	@param ogreEntity The entity to check.
            	@param line The line to intersect with the entity.
            	@param hitCoord The coordinates of the intersection.
            	@param normal The normal of the intersection coordinates.
            	@return The closest face which is hit by the ray.
            */
            const Opcode::CollisionFace OpcodeManager::getHitCoordinates( Ogre::Entity * ogreEntity, Ogre::Ray const & line, Ogre::Vector3 & hitCoord, Ogre::Vector3 & normal);
            
            /** Calculates if the ray collides with the surface of the entity.
            	@param ogreEntity The entity to check.
            	@param line The line to intersect with the entity.
            	@return The closest face which is hit by the ray.
            */
            const Opcode::CollisionFace OpcodeManager::collisionOnSurface( Ogre::Entity * ogreEntity, Ogre::Ray const & line );
            
            /** Checks if a ray collides with the surface of an Opcode model.	
            	@param ogreEntity Original Ogre entity.
            	@param opcModel Opcode model of the ogre Entity.
            	@param line Ray to check.
            	@param numFaces Number of collision faces.
            	@return The pointer to the array of collision faces.
            */
            
            Opcode::CollisionFace * OpcodeManager::lineCheck(Ogre::Entity * ogreEntity,    Opcode::Model * opcModel,   Ogre::Ray const & line,   int &numFaces);
            
            /** @todo deleteOpcodeModel documentation	
            	@param model
            	@return
            */
            void OpcodeManager::deleteOpcodeModel(Opcode::Model * model);
            
            /** @todo createOpcodeModel documentation	
            	@param ogreEntity
            	@return
            */
            Opcode::Model * OpcodeManager::createOpcodeModel(Ogre::Entity * ogreEntity);
            
            /** Converts mesh vertex and face data into simple float arrays.
            	@param entity Entity to extract data from.
            	@param vertexData Target vertex data array.
            	@param vertex_count Number of vertices.
            	@param faceData Target face data array.
            	@param index_count Number of indices.
            */
            void OpcodeManager::convertMeshData(Ogre::Entity * entity, float * vertexData, size_t vertex_count, unsigned int * faceData, size_t index_count);
            
            /** Counts how much indices (faces) and vertices a entity contains.
            	@param entity Entity to count its data.
            	@param index_count Number of indices.
            	@param vertex_count Number of vertices.
            */
            void OpcodeManager::countIndicesAndVertices(Ogre::Entity * entity, size_t & index_count, size_t & vertex_count);            
        };
} // namespace Opcode4Ogre

#endif // __OPCODE_MANAGER_H__
